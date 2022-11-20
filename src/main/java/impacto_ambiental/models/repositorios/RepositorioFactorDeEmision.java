package impacto_ambiental.models.repositorios;

import impacto_ambiental.models.entities.calculadorHC.DatoDeActividad;
import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.reportes.HChistorico;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioFactorDeEmision extends Repositorio<FactorDeEmision> {
  public RepositorioFactorDeEmision() {
    super(FactorDeEmision.class);
  }

  public FactorDeEmision buscarSegunDatoDeActividad(DatoDeActividad datoDeActividad) {

//    CriteriaBuilder criteriaBuilder = criteriaBuilder();
//    CriteriaQuery<DatoDeActividad> query = criteriaBuilder.createQuery(DatoDeActividad.class);
//    Root<DatoDeActividad> raiz = query.from(DatoDeActividad.class);
//
//    Predicate predicadoTipoConsumo = criteriaBuilder.equal(raiz.get("tipoConsumo"), datoDeActividad.getTipoConsumo());
//    Predicate predicadoTipoActividad = criteriaBuilder.equal(raiz.get("tipoActividad"), datoDeActividad.getTipoActividad());
//    Predicate predicado = criteriaBuilder.and(predicadoTipoActividad, predicadoTipoConsumo);
//    query.where(predicado);
//
//    try {
//      //Ejecución de la búsqueda
//      BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
//      //System.out.println(busqueda.getCritero() == null? "null": "noNull");
      //FactorDeEmision factorDeEmision = buscar(busqueda);
      FactorDeEmision factor = buscarTodos().stream().filter(f -> f.getTipoActividad() == datoDeActividad.getTipoActividad() && f.getTipoConsumo() == datoDeActividad.getTipoConsumo()).findFirst().get();

      return factor;
  }

  public FactorDeEmision buscarOCrearSegunDatoDeActividad(DatoDeActividad datoDeActividad, double factorDeEmision) {
    try {
      return buscarSegunDatoDeActividad(datoDeActividad);
    } catch(NoResultException nre) {
      return new FactorDeEmision(datoDeActividad.getTipoActividad(), datoDeActividad.getTipoConsumo(), factorDeEmision);
    }
  }
}