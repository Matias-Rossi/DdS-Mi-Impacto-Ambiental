package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.TipoUsuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioRoles extends Repositorio<Rol> {
    public RepositorioRoles() {
        super(Rol.class);
    }

    //Le paso un String (Que es TipoUsuario) y me retorna un rol
    public Rol obtenerRol(String tipoUsuario) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Rol> query = criteriaBuilder.createQuery(Rol.class);
        Root<Rol> raiz = query.from(Rol.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("tipo"), TipoUsuario.valueOf(tipoUsuario));
        query.where(predicado);

        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
        return buscar(busqueda);
    }


}
