package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.trayecto.Tramo;

import java.util.List;

public class RepositorioSolicitudes extends Repositorio<Solicitud> {

<<<<<<< Updated upstream
    public RepositorioSolicitudes{ super(Solicitud.class)}
    public List<Solicitud> buscarSolicitudesAceptadasPorIDMiembro(int id){
        
=======

    public RepositorioSolicitudes() {
        super(Solicitud.class);
>>>>>>> Stashed changes
    }
}
