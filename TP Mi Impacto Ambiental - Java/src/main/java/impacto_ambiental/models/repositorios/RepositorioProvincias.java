package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioProvincias extends Repositorio<Provincia> {

  public List<Provincia> getProvinciasMenos(List<Provincia> provincias){
    return null;
  }
  public RepositorioProvincias() {
    super(Provincia.class);
  }

  /**
  * Busca una provincia en la base de datos y la devuelve. Si no existe, la crea
  * @param nombreProvincia nombre de la provincia (en Enum)
  * @return provincia la provincia obtenida de la base de datos, o creada nueva
  * */
  public Provincia getProvincia(NombreProvincia nombreProvincia) {
    Provincia provinciaExistente;
    try {
      //Construcción de la query
      CriteriaBuilder criteriaBuilder = criteriaBuilder();
      CriteriaQuery<Provincia> query = criteriaBuilder.createQuery(Provincia.class);
      Root<Provincia> raiz = query.from(Provincia.class);

      Predicate predicado = criteriaBuilder.equal(raiz.get("nombreProvincia"), nombreProvincia);
      query.where(predicado);
      BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
      //System.out.println(busqueda.getCritero() == null? "null": "noNull");

      //Ejecución de la búsqueda
      provinciaExistente = buscar(busqueda);
      System.out.println("Encontrada provincia " + nombreProvincia.toString());

    } catch (NoResultException e)  {
      //Creación de la provincia
      System.out.println("Creando provincia " + nombreProvincia.toString());
      provinciaExistente = new Provincia(nombreProvincia);

      //Persistencia de la provincia nueva
      agregar(provinciaExistente);
    }

    return provinciaExistente;
  }
}
