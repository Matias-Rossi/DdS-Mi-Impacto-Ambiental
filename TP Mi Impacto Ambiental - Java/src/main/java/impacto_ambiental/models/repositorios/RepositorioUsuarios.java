package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioUsuarios extends Repositorio<Usuario> {

  public RepositorioUsuarios() {
    super(Usuario.class);
  }

  public Usuario obtenerUsuarioSegunCredenciales(String email, String contrasenia) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
    Root<Usuario> raiz = query.from(Usuario.class);

    Predicate coincidirEmail = criteriaBuilder.equal(raiz.get("usuario"), email);
    Predicate coincidirContrasenia = criteriaBuilder.equal(raiz.get("contrasenia"), contrasenia);
    Predicate coincidirCredenciales = criteriaBuilder.and(coincidirEmail, coincidirContrasenia);
    query.where(coincidirCredenciales);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return buscar(busqueda);
  }

  public Boolean existeUsuario(String email) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Usuario> query = criteriaBuilder.createQuery(Usuario.class);
    Root<Usuario> raiz = query.from(Usuario.class);

    Predicate coincidirEmail = criteriaBuilder.equal(raiz.get("usuario"), email);
    query.where(coincidirEmail);

    try {
      BusquedaConPredicado busqueda = new BusquedaConPredicado(coincidirEmail, query);
      return buscar(busqueda) != null;
    } catch (NoResultException nre) {
      return false;
    }
  }
}
