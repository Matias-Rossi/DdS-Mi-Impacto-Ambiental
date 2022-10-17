package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.trayecto.Tramo;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioSolicitudes extends Repositorio<Solicitud> {
    public RepositorioSolicitudes(){ super(Solicitud.class);}
    public List<Solicitud> buscarSolicitudesAceptadasPorIDMiembro(int idMiembro){

        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Solicitud> query = criteriaBuilder.createQuery(Solicitud.class);
        Root<Solicitud> raiz = query.from(Solicitud.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("miembro_id"), idMiembro);
        query.where(predicado);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);

        return (List<Solicitud>) buscarLista(busqueda);

    }
}
