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

  public List<Tramo> listarTramosDeTrayecto(Integer idTrayecto) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();

    EntityManager em = EntityManagerHelper.getEntityManager();
    Query query = em.createQuery("SELECT tram FROM Trayecto tray JOIN tray.tramo_id i WHERE i.trayecto_id=?1", Tramo.class);
    query.setParameter(1, idTrayecto);
    //TODO Probar si funciona

    //Ejecución de la búsqueda
    return query.getResultList();
  }
}
