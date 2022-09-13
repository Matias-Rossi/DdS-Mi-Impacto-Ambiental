package domain.context;

import domain.calculadorHC.*;
import domain.importadorExcel.ActividadBase;
import domain.importadorExcel.ApachePOI;
import domain.perfil.*;
import domain.persistenceExtend.EntityManagerHelper;
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
        FactorDeEmision fac = CalculadorDeHC.getInstance().devolverFactorDeEmision(dato);
        assertEquals(0.5,fac.getFactorEmision());
    }
    @Test
    public void pruebasHidratacion() throws IOException {
        Provincias bsAs = Provincias.obtenerProvincia(Provincia.Buenos_Aires);
        MunicipiosODepartamentos chivilcoy = bsAs.crearMunicipio("Chivilcoy");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = chivilcoy.crearOrganizacion(ApachePOI.getInstance(),"razon",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Area area = organizacion.darAltaArea("area");
        Ubicacion ubicacion = new Ubicacion(chivilcoy,"loc", "cp", "cal", 1);
        Miembro miembro = new Miembro("nombre",  "apellido", TipoDocumento.DNI, 12345678,ubicacion,"mail","pass");
        Solicitud solicitud = miembro.darseAltaEnOrganizacion(area);
        area.gestionarMiembrosPendientes(solicitud, SolicitudEstado.ACEPTADA);
        List<Organizacion> organizacions = new ArrayList<Organizacion>();
        organizacions.add(organizacion);
        Trayecto trayecto = miembro.generarTrayecto("descripcion", organizacions, 2022, 1, 28);
        Ubicacion llegada = new Ubicacion(chivilcoy,"Chivilcoy", "231", "man", 65);
        Ubicacion salida = new Ubicacion(chivilcoy, "Chivilcoy", "532", "bol", 23);
        SubTipoTransporte subtipo = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"subtipo1");
        Particular particular = new Particular(subtipo, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(),0.5);
        Tramo tramo = trayecto.aniadirNuevoTramo(salida,llegada,particular);
        EntityManagerHelper.update(bsAs); //Cambiado de persist
    }
    @Test
    public void traerHidratacion(){
        Provincias bsas = Provincias.obtenerProvincia(Provincia.Buenos_Aires);//(Provincias) EntityManagerHelper.createQuery("from Provincias where provincia = 'Buenos_Aires'");
        MunicipiosODepartamentos chivilcoy = (MunicipiosODepartamentos) EntityManagerHelper.createQuery("from MunicipiosODepartamentos where municipioOLocalidad ='Chivilcoy'");
        assertEquals("Buenos Aires",chivilcoy.getProvincia().toString());
    }
}
