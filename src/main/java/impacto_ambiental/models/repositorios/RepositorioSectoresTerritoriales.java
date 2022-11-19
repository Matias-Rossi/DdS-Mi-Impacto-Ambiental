package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.BusquedaConPredicado;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioSectoresTerritoriales extends Repositorio<SectorTerritorial> {

    public RepositorioSectoresTerritoriales() {
        super(SectorTerritorial.class);
    }

    public SectorTerritorial getSectorDesdeNombre(String sector) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<SectorTerritorial> query = criteriaBuilder.createQuery(SectorTerritorial.class);
        Root<SectorTerritorial> raiz = query.from(SectorTerritorial.class);

        Predicate predicado = criteriaBuilder.equal(raiz.get("sector"), sector);
        query.where(predicado);
        BusquedaConPredicado busqueda = new BusquedaConPredicado(null, query);
        System.out.println(busqueda.getCritero() == null? "null": "noNull");

        //Ejecución de la búsqueda
        return buscar(busqueda);
    }

}
