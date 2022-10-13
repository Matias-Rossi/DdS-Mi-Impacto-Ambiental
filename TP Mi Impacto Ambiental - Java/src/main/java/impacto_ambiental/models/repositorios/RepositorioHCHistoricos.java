package impacto_ambiental.models.repositorios;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioHCHistoricos extends Repositorio<HChistorico> {

  public RepositorioHCHistoricos() {
    super(HChistorico.class);
  }

  public List<HChistorico> getReportesDeOrganizacion(Organizacion organizacion){
    //Construcción de la query
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<HChistorico> query = criteriaBuilder.createQuery(HChistorico.class);
    Root<HChistorico> raiz = query.from(HChistorico.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("organizacion_id"), organizacion.getId());
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    //Ejecución de la búsqueda
    return (List<HChistorico>) buscarLista(busqueda);
  }


  public List<HChistorico> getReportesDeOrganizacionConClasificacion(Clasificacion clasificacion){

    //Construcción de la query
    EntityManager em = EntityManagerHelper.getEntityManager();
    Query query = em.createQuery("SELECT r FROM HChistorico r LEFT JOIN FETCH r.organizacion o WHERE o.clasificacion.id=?1", HChistorico.class);
    query.setParameter(1, clasificacion.getId());

    //Ejecución de la búsqueda
    return query.getResultList();

    //TODO: Todo esto va a haber que pasarlo al controller
    //REEMPLAZA A EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.clasificacion = :"+clasificacion);
  }


  public List<HChistorico> getReportesDeMunicipio(MunicipiosODepartamentos municipio){
    //Construcción de la query
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<HChistorico> query = criteriaBuilder.createQuery(HChistorico.class);
    Root<HChistorico> raiz = query.from(HChistorico.class);

    Join<HChistorico, Organizacion> organizacion = raiz.join("municipio_id", JoinType.INNER);
    Predicate clasif = criteriaBuilder.equal(raiz.get("municipio_id"), municipio.getId());
    query.where(clasif);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    //Ejecución de la búsqueda
    return (List<HChistorico>) buscarLista(busqueda);

    //REEMPLAZA A EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento = :"+municipio);
  }


  public List<HChistorico> getReportesDeTodasLasProvinciasMenos(List<HChistorico> provincia){
    //TODO: Pasar a CriteriaBuilder

    //REEMPLAZA A return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
    return new ArrayList<HChistorico>();
  }

}
