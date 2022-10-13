package impacto_ambiental.db;

import lombok.Getter;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

public class BusquedaConPredicado {
  @Getter
  private Predicate predicado;
  @Getter
  private CriteriaQuery critero;

  public BusquedaConPredicado(Predicate predicado, CriteriaQuery criterio) {
    this.predicado = predicado;
    this.critero = criterio;
  }


}
