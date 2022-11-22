package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.notificaciones.Recomendacion;
import impacto_ambiental.models.entities.usuario.Rol;

public class RepositorioRecomendaciones extends Repositorio<Recomendacion> {
    public RepositorioRecomendaciones() {
        super(Recomendacion.class);
    }
}
