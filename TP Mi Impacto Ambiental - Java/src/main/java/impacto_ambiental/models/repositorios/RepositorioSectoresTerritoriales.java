package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;

public class RepositorioSectoresTerritoriales extends Repositorio<SectorTerritorial> {

    public RepositorioSectoresTerritoriales() {
        super(SectorTerritorial.class);
    }
}
