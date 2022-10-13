package impacto_ambiental.models.repositorios;

import impacto_ambiental.models.entities.calculadorHC.DatoDeActividad;
import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioFactorDeEmision extends Repositorio<FactorDeEmision> {
  public RepositorioFactorDeEmision() {
    super(FactorDeEmision.class);
  }

  public FactorDeEmision buscarSegunDatoDeActividad(DatoDeActividad datoDeActividad) {
    //Construcción de la query
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<FactorDeEmision> query = criteriaBuilder.createQuery(FactorDeEmision.class);
    Root<FactorDeEmision> raiz = query.from(FactorDeEmision.class);

    Predicate predicadoTipoConsumo = criteriaBuilder.equal(raiz.get("tipoConsumo"), datoDeActividad.getTipoConsumo());
    Predicate predicadoTipoActividad = criteriaBuilder.equal(raiz.get("tipoActividad"), datoDeActividad.getTipoActividad());
    Predicate predicado = criteriaBuilder.and(predicadoTipoActividad, predicadoTipoConsumo);
    query.where(predicado);

    try {
      //Ejecución de la búsqueda
      BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
      //System.out.println(busqueda.getCritero() == null? "null": "noNull");
      return buscar(busqueda);

    } catch (NoResultException nre) {
      System.out.println("No se encontró el Factor de Emisión según el Dato de Actividad brindado");
      throw nre;
    }
  }

  public FactorDeEmision buscarOCrearSegunDatoDeActividad(DatoDeActividad datoDeActividad, double factorDeEmision) {
    try {
      return buscarSegunDatoDeActividad(datoDeActividad);
    } catch(NoResultException nre) {
      return new FactorDeEmision(datoDeActividad.getTipoActividad(), datoDeActividad.getTipoConsumo(), factorDeEmision);
    }
  }
}