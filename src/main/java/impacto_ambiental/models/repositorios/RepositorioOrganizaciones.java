package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.db.Repositorio;
import spark.Request;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizaciones extends Repositorio<Organizacion> {
  public RepositorioOrganizaciones() {
    super(Organizacion.class);
  }

  public List<Organizacion> getOrganizacionesPorClasificacion(Clasificacion clasificacion){
    return null;
  }

  public Organizacion buscarPorIDUsuario(int idUsuario) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Organizacion> query = criteriaBuilder.createQuery(Organizacion.class);
    Root<Organizacion> raiz = query.from(Organizacion.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("usuario"), idUsuario);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return buscar(busqueda);
  }

  public List<Solicitud> obtenerSolicitudes(Organizacion organizacion) {
    List<Area> areas = organizacion.getAreas();
    List<Solicitud> solicitudes = new ArrayList<>();
    areas.forEach((a) -> {solicitudes.addAll(a.getSolicitudes());});
    return solicitudes;
  }

  public Organizacion obtenerOrganizacionSegunRequest(Request request) {
    return this.buscarPorIDUsuario(request.session().attribute("id"));
  }

}
