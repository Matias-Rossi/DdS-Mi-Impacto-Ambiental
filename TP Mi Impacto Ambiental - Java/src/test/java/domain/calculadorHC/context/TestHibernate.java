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
        assertEquals(2.0,GeneradorDeReportes.getInstance().hCTotalPorTipoDeOrganizacion(clasificacion));
    }
    @Test
    public void reportesHcTotalSectorTerritorial(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion3);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().hCTotalPorSectorTerriorial(vdp));
    }
    @Test
    public void reportesCompSectorTerr(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(vdp).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesCompPais(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        Provincias tucu = new Provincias(Provincia.Tucuman);
        Provincias salta = new Provincias(Provincia.Salta);
        Provincias mendoza = new Provincias(Provincia.Mendoza);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        MunicipiosODepartamentos tucuMuni = tucu.crearMunicipio("das");
        MunicipiosODepartamentos saltaMuni = salta.crearMunicipio("asdgad");
        MunicipiosODepartamentos mendoMuni = mendoza.crearMunicipio("dfd");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion4 = tucuMuni.crearOrganizacion(ApachePOI.getInstance(),"gdsv",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion5 = saltaMuni.crearOrganizacion(ApachePOI.getInstance(),"rhwrh",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion6 = mendoMuni.crearOrganizacion(ApachePOI.getInstance(),"sfgfb",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);

        EntityManagerHelper.persist(prova);
        EntityManagerHelper.persist(tucu);
        EntityManagerHelper.persist(salta);
        EntityManagerHelper.persist(mendoza);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes cuarto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion3);
        Reportes quinto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion4);
        Reportes sexto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion5);
        Reportes septimo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion6);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        EntityManagerHelper.persist(cuarto);
        EntityManagerHelper.persist(quinto);
        EntityManagerHelper.persist(sexto);
        EntityManagerHelper.persist(septimo);
        List<Provincias> provincias = new ArrayList<>();
        provincias.add(prova);
        provincias.add(tucu);
        assertEquals(4.0,GeneradorDeReportes.getInstance().composicionDeaHCTotalANivelPais(provincias).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesCompSectorOrg(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().composicionDeHCDeUnaOrganizacion(organizacion).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesHistoricos(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2021, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2022,GeneradorDeReportes.getInstance().EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(vdp).getAnioFin());
    }
}
