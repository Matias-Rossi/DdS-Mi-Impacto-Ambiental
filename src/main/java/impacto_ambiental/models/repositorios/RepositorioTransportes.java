package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.Linea;
import impacto_ambiental.models.entities.transporte.TipoCombustible;
import impacto_ambiental.models.entities.transporte.Transporte;
import impacto_ambiental.models.entities.transporte.TransportePublico;
import org.hibernate.Criteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioTransportes extends Repositorio<Transporte> {
    public RepositorioTransportes(){
        super(Transporte.class);
    }
    public Transporte encontrar(Integer subTipoTransporte, TipoCombustible tipoCombustible){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Transporte> query = criteriaBuilder.createQuery(Transporte.class);
        Root<Transporte> raiz = query.from(Transporte.class);

        Predicate predicadoSubTipo = criteriaBuilder.equal(raiz.get("subTipoTransporte"), subTipoTransporte);
        Predicate predicadoCombustible = criteriaBuilder.equal(raiz.get("combustible"), tipoCombustible.toString());
        Predicate predicados = criteriaBuilder.and(predicadoSubTipo, predicadoCombustible);
        query.where(predicados);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return (Transporte) buscar(busqueda);
    }
    public Transporte encontrar(Integer subTipoTransporte){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Transporte> query = criteriaBuilder.createQuery(Transporte.class);
        Root<Transporte> raiz = query.from(Transporte.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("subTipoTransporte"), subTipoTransporte);
        query.where(predicado);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return (Transporte) buscar(busqueda);
    }

}
