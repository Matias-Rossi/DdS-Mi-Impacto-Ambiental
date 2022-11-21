package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.SubTipoTransporte;
import impacto_ambiental.models.entities.transporte.TipoTransporte;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioSubTipoTransportes extends Repositorio<SubTipoTransporte> {
    public RepositorioSubTipoTransportes(){super(SubTipoTransporte.class);}

    public List<SubTipoTransporte> obtenerSubTiposPublicos() {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<SubTipoTransporte> query = criteriaBuilder.createQuery(SubTipoTransporte.class);
        Root<SubTipoTransporte> raiz = query.from(SubTipoTransporte.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("tipo"), TipoTransporte.TIPO_PUBLICO);
        query.where(predicado);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return (List<SubTipoTransporte>) buscarLista(busqueda);
    }
}
