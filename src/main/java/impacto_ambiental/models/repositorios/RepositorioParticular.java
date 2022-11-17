package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.Particular;
import impacto_ambiental.models.entities.transporte.TipoCombustible;
import impacto_ambiental.models.entities.transporte.Transporte;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioParticular extends Repositorio<Particular> {
    public RepositorioParticular(){
        super(Particular.class);
    }
    public Particular encontrar(Integer subTipoTransporte, String tipoCombustible){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Particular> query = criteriaBuilder.createQuery(Particular.class);
        Root<Particular> raiz = query.from(Particular.class);

        Predicate predicadoSubTipo = criteriaBuilder.equal(raiz.get("subTipoTransporte"), subTipoTransporte);
        Predicate predicadoCombustible = criteriaBuilder.equal(raiz.get("combustible"), TipoCombustible.valueOf( tipoCombustible));
        Predicate predicados = criteriaBuilder.and(predicadoSubTipo, predicadoCombustible);
        query.where(predicados);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return (Particular) buscar(busqueda);
    }
}
