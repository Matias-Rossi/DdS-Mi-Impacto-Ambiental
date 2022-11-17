package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Clasificacion;

public class RepositorioClasificacion extends Repositorio<Clasificacion> {
    public RepositorioClasificacion() {
        super(Clasificacion.class);
    }

}
