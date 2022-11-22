package domain.context;

import impacto_ambiental.models.entities.notificaciones.Recomendacion;
import impacto_ambiental.models.repositorios.RepositorioRecomendaciones;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class crearRecomendacion {

    @Test
    public void crearRecomendacion() throws Exception {
        RepositorioRecomendaciones repositorioRecomendaciones = new RepositorioRecomendaciones();
        Recomendacion recomendacion= new Recomendacion("TITULO","SUBTITULO","TEXTO");
        repositorioRecomendaciones.agregar(recomendacion);
        assertNotNull( recomendacion );

    }
}
