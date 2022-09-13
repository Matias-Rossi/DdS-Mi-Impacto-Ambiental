package domain.context;

import domain.calculadorHC.*;
import domain.importadorExcel.ActividadBase;
import domain.importadorExcel.ApachePOI;
import domain.perfil.*;
import domain.persistenceExtend.EntityManagerHelper;
import domain.reportes.GeneradorDeReportes;
import domain.reportes.Periodo;
import domain.reportes.Reportes;
import domain.servicios.geodds.GeoDdsAPI;
import domain.servicios.geodds.ServicioGeoDds;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHibernate {
    @Test
    public void persisting() {
        FactorDeEmision factorDeEmision = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
        EntityManagerHelper.delete(factorDeEmision);
        EntityManagerHelper.persist(factorDeEmision);
    }
    @Test
    public void hidratarFactorDeEmision(){
        DatoDeActividad dato = new DatoDeActividad(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,0.5);
        FactorDeEmision fac =CalculadorDeHC.getInstance().devolverFactorDeEmision(dato);
        assertEquals(0.5,fac.getFactorEmision());
    }
    @Test
    public void pruebasHidratacion() throws IOException {
        Provincias bsAs = new Provincias(Provincia.Buenos_Aires);
        MunicipiosODepartamentos vdp = bsAs.crearMunicipio("vdp");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razon",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Area area = organizacion.darAltaArea("area");
        Ubicacion ubicacion = new Ubicacion(vdp,"loc","cp","cal",1);
        Miembro miembro = new Miembro(  "nombre",  "apellido", TipoDocumento.DNI, 12345678,ubicacion,"mail","pass");
        Solicitud solicitud = miembro.darseAltaEnOrganizacion(area);
        area.gestionarMiembrosPendientes(solicitud, SolicitudEstado.ACEPTADA);
        List<Organizacion> organizacions= new ArrayList<Organizacion>();
        organizacions.add(organizacion);
        Trayecto trayecto = miembro.generarTrayecto(    "descripcion",organizacions,2022,1,28);
        Ubicacion llegada = new Ubicacion(vdp,"vdp","231","man",65);
        Ubicacion salida = new Ubicacion(vdp,"vpr","532","bol",23);
        SubTipoTransporte subtipo = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"subtipo1");
        Particular particular = new Particular(subtipo, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(),0.5);
        Tramo tramo =trayecto.aniadirNuevoTramo(salida,llegada,particular);
        EntityManagerHelper.persist(bsAs);
    }
    @Test
    public void traerHidratacion(){
        Provincias bsas= (Provincias) EntityManagerHelper.createQuery("from Provincias where provincia = 'Buenos_Aires'");
        MunicipiosODepartamentos vdp = (MunicipiosODepartamentos) EntityManagerHelper.createQuery("from MunicipiosODepartamentos where municipioOLocalidad ='vdp'");
        assertEquals("Buenos Aires",vdp.getProvincia().toString());
    }
    @Test
    public void reportes(){
        Provincias prova = new Provincias(Provincia.Buenos_Aires);
        Provincias provb = new Provincias(Provincia.Catamarca);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos vpr = provb.crearMunicipio("vpr");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Clasificacion clasificacionb = new Clasificacion("clasificacionb");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razon",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacionb = vpr.crearOrganizacion(ApachePOI.getInstance(),"razonb",Tipo.INSTITUCION,clasificacion,"locb","cpb","calb",1);
        EntityManagerHelper.persist(prova);
        EntityManagerHelper.persist(provb);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacionb);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacionb);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        assertEquals(0.0,GeneradorDeReportes.getInstance().hCTotalPorTipoDeOrganizacion(clasificacion));
    }
}
