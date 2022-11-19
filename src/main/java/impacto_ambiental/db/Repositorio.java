package impacto_ambiental.db;

import impacto_ambiental.models.entities.perfil.Organizacion;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class Repositorio<T> {
  private final Class<T> tipo;

  public Repositorio(Class<T> tipo) {
    this.tipo = tipo;
  }

  public List<T> buscarTodos() {
    CriteriaBuilder builder = EntityManagerHelper.getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> criterios = builder.createQuery(this.tipo);
    criterios.from(tipo);
    List<T> entidades = EntityManagerHelper.getEntityManager().createQuery(criterios).getResultList();
    return entidades;
  }

  public T buscar(int id) {
    return EntityManagerHelper.getEntityManager().find(tipo, id);
  }
  public T buscarPorId(int id) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<T> query = criteriaBuilder.createQuery(this.tipo);
    Root<T> raiz = query.from(this.tipo);

    Predicate predicado = criteriaBuilder.equal(raiz.get("id"), id);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return buscar(busqueda);
  }


  public T buscar(BusquedaConPredicado busq) {
    return (T) EntityManagerHelper.getEntityManager().createQuery(busq.getCritero()).getSingleResult();
  }

  public T buscar(String sentencia) {
    //Hacer que se le pueda pasar una
    Query query = EntityManagerHelper.getEntityManager().createQuery(sentencia);
    return (T) query.getSingleResult();
  }


  public T buscarLista(BusquedaConPredicado busq) {
    return (T) EntityManagerHelper.getEntityManager().createQuery(busq.getCritero()).getResultList();
  }

  public T buscarLista(String sentencia) {
    Query query = EntityManagerHelper.getEntityManager().createQuery(sentencia);
    return (T) query.getResultList();
  }


  public void agregar(Object objeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().persist(objeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }

  public void actualizar(Object objeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().merge(objeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }

  public void remover(Object objeto) {
    EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().remove(objeto);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
  }

  public static CriteriaBuilder criteriaBuilder(){
    return EntityManagerHelper.getEntityManager().getCriteriaBuilder();
  }

}
