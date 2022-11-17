package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;

public class RepositorioUbicaciones extends Repositorio<Ubicacion> {
    public RepositorioUbicaciones(){ super(Ubicacion.class);}
}

