package domain.context;

import domain.calculadorHC.*;
import domain.importadorExcel.ApachePOI;
import domain.perfil.*;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.repositorios.RepositorioFactorDeEmision;
import domain.persistenceExtend.repositorios.RepositorioMunicipiosODepartamentos;
import domain.persistenceExtend.repositorios.RepositorioProvincias;
import domain.persistenceExtend.repositorios.RepositorioReportes;
import domain.reportes.GeneradorDeReportes;
import domain.reportes.Periodo;
import domain.reportes.Reporte;
import domain.servicios.geodds.ServicioGeoDds;
import domain.servicios.geodds.entidades.Municipio;
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
    private RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

    @Test
    public void hidratarFactorDeEmision(){
        //Carga a db
        RepositorioFactorDeEmision repositorio = new RepositorioFactorDeEmision();
        FactorDeEmision factorDeEmisionSaliente = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
        repositorio.actualizar(factorDeEmisionSaliente);

        //Fetch de db
        DatoDeActividad dato = new DatoDeActividad(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,0.5);
        FactorDeEmision fac = CalculadorDeHC.getInstance().devolverFactorDeEmision(dato);
        assertEquals(0.5, fac.getFactorEmision());
    }

    @Test
    public void pruebasHidratacion() throws IOException {
        Provincia bsAs = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
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
        repositorioProvincias.actualizar(bsAs); //Cambiado de persist
    }
    @Test
    public void traerHidratacion(){
        Provincia bsas = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        MunicipiosODepartamentos saliente = repositorioMunicipiosODepartamentos.getMunicipio(bsas, "Chivilcoy");
        repositorioMunicipiosODepartamentos.actualizar(saliente);
        MunicipiosODepartamentos entrante = repositorioMunicipiosODepartamentos.getMunicipioDesdeNombre("Chivilcoy");
        assertEquals("Buenos Aires",entrante.getProvincia().toString());
    }

}
