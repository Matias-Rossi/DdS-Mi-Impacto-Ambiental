package domain.persistenceExtend.repositorios;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.BusquedaConPredicado;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.Repositorio;
import domain.reportes.Reporte;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincia;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioReportes extends Repositorio<Reporte> {

  public RepositorioReportes() {
    super(Reporte.class);
  }

  public List<Reporte> getReportesDeOrganizacion(Organizacion organizacion){
    //Construcción de la query
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Reporte> query = criteriaBuilder.createQuery(Reporte.class);
    Root<Reporte> raiz = query.from(Reporte.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("organizacion_id"), organizacion.getId());
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    //Ejecución de la búsqueda
    return (List<Reporte>) buscarLista(busqueda);
  }


  public List<Reporte> getReportesDeOrganizacionConClasificacion(Clasificacion clasificacion){

    //Construcción de la query
    EntityManager em = EntityManagerHelper.getEntityManager();
    Query query = em.createQuery("SELECT r FROM Reporte r LEFT JOIN FETCH r.organizacion o WHERE o.clasificacion.id=?1", Reporte.class);
    query.setParameter(1, clasificacion.getId());

    //Ejecución de la búsqueda
    return query.getResultList();

    //TODO: Todo esto va a haber que pasarlo al controller
    //REEMPLAZA A EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.clasificacion = :"+clasificacion);
  }


  public List<Reporte> getReportesDeMunicipio(MunicipiosODepartamentos municipio){
    //Construcción de la query
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Reporte> query = criteriaBuilder.createQuery(Reporte.class);
    Root<Reporte> raiz = query.from(Reporte.class);

    Join<Reporte, Organizacion> organizacion = raiz.join("municipio_id", JoinType.INNER);
    Predicate clasif = criteriaBuilder.equal(raiz.get("municipio_id"), municipio.getId());
    query.where(clasif);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    //Ejecución de la búsqueda
    return (List<Reporte>) buscarLista(busqueda);

    //REEMPLAZA A EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento = :"+municipio);
  }


  public List<Reporte> getReportesDeTodasLasProvinciasMenos(List<Reporte> provincia){
    //TODO: Pasar a CriteriaBuilder

    //REEMPLAZA A return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
    return new ArrayList<Reporte>();
  }

}
