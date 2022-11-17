package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Provincia;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioMunicipiosODepartamentos extends Repositorio<MunicipiosODepartamentos> {

  public RepositorioMunicipiosODepartamentos() {
    super(MunicipiosODepartamentos.class);
  }

  /**
   * Busca un municipio en la base de datos y lo devuelve. Si no existe, lo crea
   * @param provincia provincia donde se ubica el municipio o departamento
   * @param nombreMunicipio nombre de la municipio
   * @return municipio obtenido de la base de datos, o creado nuevo
   * */
  public MunicipiosODepartamentos getMunicipio(Provincia provincia, String nombreMunicipio) {
    MunicipiosODepartamentos municipioExistente;
    try {
      //Construcción de la query
      CriteriaBuilder criteriaBuilder = criteriaBuilder();
      CriteriaQuery<MunicipiosODepartamentos> query = criteriaBuilder.createQuery(MunicipiosODepartamentos.class);
      Root<MunicipiosODepartamentos> raiz = query.from(MunicipiosODepartamentos.class);

      Predicate predicado = criteriaBuilder.equal(raiz.get("municipioOLocalidad"), nombreMunicipio);
      query.where(predicado);
      BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
      System.out.println(busqueda.getCritero() == null? "null": "noNull");

      //Ejecución de la búsqueda
      municipioExistente = buscar(busqueda);
      System.out.println("Encontrado municipio " + nombreMunicipio);

    } catch (NoResultException e)  {
      //Creación del municipio
      System.out.println("Creando municipio " + nombreMunicipio);
      municipioExistente = new MunicipiosODepartamentos(provincia, nombreMunicipio);

      //Persistencia del municipio
      agregar(municipioExistente);
    }

    return municipioExistente;
  }

  public MunicipiosODepartamentos getMunicipioDesdeNombre(String nombreMunicipio) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<MunicipiosODepartamentos> query = criteriaBuilder.createQuery(MunicipiosODepartamentos.class);
    Root<MunicipiosODepartamentos> raiz = query.from(MunicipiosODepartamentos.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("municipioOLocalidad"), nombreMunicipio);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
    System.out.println(busqueda.getCritero() == null? "null": "noNull");

    //Ejecución de la búsqueda
    return buscar(busqueda);
  }

}
