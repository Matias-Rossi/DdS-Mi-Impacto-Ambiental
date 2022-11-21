package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.notificaciones.Contacto;

public class RepositorioContactos extends Repositorio<Contacto> {
    public RepositorioContactos(){super(Contacto.class);}
}
