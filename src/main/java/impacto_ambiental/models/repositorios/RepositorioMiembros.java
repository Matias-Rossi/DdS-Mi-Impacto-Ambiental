package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Miembro;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioMiembros extends Repositorio<Miembro> {
    public RepositorioMiembros() {
        super(Miembro.class);
    }

    public Miembro buscarPorIDUsuario(int idUsuario) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Miembro> query = criteriaBuilder.createQuery(Miembro.class);
        Root<Miembro> raiz = query.from(Miembro.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("usuario"), idUsuario);
        query.where(predicado);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return buscar(busqueda);
    }
}
