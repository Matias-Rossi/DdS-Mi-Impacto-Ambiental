package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.SubTipoTransporte;

public class RepositorioSubTipoTransportes extends Repositorio<SubTipoTransporte> {
    public RepositorioSubTipoTransportes(){super(SubTipoTransporte.class);}
}
