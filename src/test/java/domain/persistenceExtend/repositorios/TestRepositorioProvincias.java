package domain.persistenceExtend.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.transporte.Transporte;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioSolicitudes;
import org.junit.jupiter.api.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRepositorioProvincias {

    @Test
    public void testaa(){
        RepositorioSolicitudes repositorioSolicitudes = new RepositorioSolicitudes();
        List<Solicitud> solicitudes = repositorioSolicitudes.buscarSolicitudesAceptadasPorIDMiembro(1);

        List<Area> areas = (List<Area>) solicitudes.stream().map(Solicitud::getArea).toList();
    }

    @Test
    public void testUsuario(){
        String query = "from " + Usuario.class.getName() + " WHERE usuario = 'admin@gmail.com' AND contrasenia='1234'";

        Usuario email = (Usuario) EntityManagerHelper
                .getEntityManager()
                .createQuery(query)
                .getSingleResult();

        assertEquals(email.getId(), 1);
    }

  /*
  @Test
  @DisplayName("Provincia puede ser actualizada")
  public void testRepositorioProvincias() throws IOException {
    //Creación de provincia/municipio
    RepositorioProvincias repositorioProvincia = new RepositorioProvincias();
    Provincia bsAs = repositorioProvincia.getProvincia(NombreProvincia.Buenos_Aires);
    MunicipiosODepartamentos chivilcoy = bsAs.crearMunicipio("Chivilcoy");

    //Creación de organización
    Clasificacion clasificacion = new Clasificacion("clasificacion");
    Organizacion organizacion = chivilcoy.crearOrganizacion(ApachePOI.getInstance(),"razon", Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
    Area area = organizacion.darAltaArea("area");
    Ubicacion ubicacion = new Ubicacion(chivilcoy,"loc", "cp", "cal", 1);

    //Gestión de miembro
    Miembro miembro = new Miembro("nombre",  "apellido", TipoDocumento.DNI, 12345678,ubicacion,"mail","pass");
    Solicitud solicitud = miembro.darseAltaEnOrganizacion(area);
    area.gestionarMiembrosPendientes(solicitud, SolicitudEstado.ACEPTADA);

    //Agregar organización a la provncia
    List<Organizacion> organizaciones = new ArrayList<Organizacion>();
    organizaciones.add(organizacion);

    //Creación del trayecto/tramo
    Trayecto trayecto = miembro.generarTrayecto("descripcion", organizaciones, 2022, 1, 28);
    Ubicacion llegada = new Ubicacion(chivilcoy,"Chivilcoy", "231", "man", 65);
    Ubicacion salida = new Ubicacion(chivilcoy, "Chivilcoy", "532", "bol", 23);
    SubTipoTransporte subtipo = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"subtipo1");
    Particular particular = new Particular(subtipo, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(),0.5);
    Tramo tramo = trayecto.aniadirNuevoTramo(salida,llegada,particular);

    //Actualización a la base de datos
    repositorioProvincia.actualizar(bsAs);

    //Fetch de la base de datos
    Provincia provinciaFetcheada = repositorioProvincia.getProvincia(NombreProvincia.Buenos_Aires);

    //Comparación de los datos
    assertEquals(bsAs, provinciaFetcheada);
  }

   */
}
