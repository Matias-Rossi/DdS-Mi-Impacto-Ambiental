package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class RepositorioTramos extends Repositorio<Tramo> {

  public RepositorioTramos() {
    super(Tramo.class);
  }

  public List<Tramo> listarTramosDeTrayecto(Integer idTrayecto) {/*
    EntityManager em = EntityManagerHelper.getEntityManager();
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Tramo> query = criteriaBuilder.createQuery(Tramo.class);
    Root<Tramo> raiz = query.from(Tramo.class);
    Join<Tramo, Trayecto> join = raiz.join("trayectos");
    Predicate predicado = criteriaBuilder.equal(join.get("id"), idTrayecto);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
    return (List<Tramo>) buscarLista(busqueda);*/
    return null;
  }
}
