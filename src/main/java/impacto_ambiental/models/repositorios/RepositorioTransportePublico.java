package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.Linea;
import impacto_ambiental.models.entities.transporte.SubTipoTransporte;
import impacto_ambiental.models.entities.transporte.TransportePublico;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioTransportePublico extends Repositorio<TransportePublico> {

  public RepositorioTransportePublico() {
    super(TransportePublico.class);
  }

  public List<Linea> lineasSegunTransporte(TransportePublico transportePublico) {
    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Linea> query = criteriaBuilder.createQuery(Linea.class);
    Root<Linea> raiz = query.from(Linea.class);

    Predicate predicado = criteriaBuilder.equal(raiz.get("transporte"), transportePublico);
    query.where(predicado);
    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return (List<Linea>) buscarLista(busqueda);
  }


  public List<Linea> buscarLineasPorSubtipo(String subTipo) {
    RepositorioLineas repositorioLineas = new RepositorioLineas();

    CriteriaBuilder criteriaBuilder = criteriaBuilder();
    CriteriaQuery<Linea> query = criteriaBuilder.createQuery(Linea.class);
    Root<Linea> linea = query.from(Linea.class);
    Root<TransportePublico> tp = query.from(TransportePublico.class);
    Root<SubTipoTransporte> st = query.from(SubTipoTransporte.class);

    query.select(linea);
    query.where(
        criteriaBuilder.and(
            criteriaBuilder.equal(linea.get("id"), tp.get("linea")),
            criteriaBuilder.equal(st.get("id"), tp.get("subTipoTransporte")),
            criteriaBuilder.equal(st.get("subTipo"), subTipo)
        )
    );

    BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

    return (List<Linea>) buscarLista(busqueda);

    /*
    List<TransportePublico> transportePublicos = buscarTodos();
    System.out.println("##### Encontrados " + transportePublicos.size() + " subtipos");
    List<Linea> lineas = transportePublicos.stream()
        .filter(tp -> tp.getSubTipoTransporte().equals(subTipo))
        .map(tp -> tp.getLinea())
        .collect(Collectors.toList());

    System.out.println("##### Tras el fintrado quedan " + lineas.size() + " lineas");

    return lineas;

     */
  }
}
