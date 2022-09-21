package domain.persistenceExtend.repositorios;

import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.FactorDeEmision;
import domain.persistenceExtend.BusquedaConPredicado;
import domain.persistenceExtend.Repositorio;

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

    Predicate predicadoTipoConsumo = criteriaBuilder.equal(raiz.get("tipoConsumo"), datoDeActividad.getTipoConsumoDA());
    Predicate predicadoTipoActividad = criteriaBuilder.equal(raiz.get("tipoActividad"), datoDeActividad.getTipoActividadDA());
    Predicate predicado = criteriaBuilder.and(predicadoTipoActividad, predicadoTipoConsumo);
    query.where(predicado);

    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
    System.out.println(busqueda.getCritero() == null? "null": "noNull");

    //Ejecución de la búsqueda
    return buscar(busqueda);
  }
}