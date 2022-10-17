package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.trayecto.Trayecto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioTrayectos extends Repositorio<Trayecto> {

  public RepositorioTrayectos() {
    super(Trayecto.class);
  }

  public List<Trayecto> listarTrayectosSegunIdMiembro(int idMiembro) {

    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Trayecto> query = criteriaBuilder.createQuery(Trayecto.class);
    Root<Trayecto> raiz = query.from(Trayecto.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("miembro_id"), idMiembro);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return (List<Trayecto>) buscarLista(busqueda);
  }
  //quiero listar los trayectos de un usuario que tienen a una organizacion en su lista de organizaciones

}
