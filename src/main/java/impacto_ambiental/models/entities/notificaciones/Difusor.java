package impacto_ambiental.models.entities.notificaciones;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;

import java.util.ArrayList;
import java.util.List;

public class Difusor {
  private GestorNotificaciones gestorNotificaciones;

  public Difusor(GestorNotificaciones gestorNotificaciones) {
    this.gestorNotificaciones = gestorNotificaciones;
  }

  public void difundirNotificacion(Notificacion notificacion) {
    List<Organizacion> organizaciones = new RepositorioOrganizaciones().buscarTodos();
    System.out.println("Difundiendo notificación a organizaciones...");
    organizaciones.forEach(organizacion -> {
      organizacion.getContactos().forEach(contacto -> {
        this.gestorNotificaciones.enviarNotificacion(contacto, notificacion);
      });
    });
  }

  public void difundirRecomendaciones() {
    System.out.println("Difundiendo recomendaciones");

    //TODO USO PRUEBA
    /*
    Organizacion org = new Organizacion(
        new ApachePOI(),
        new Ubicacion(
            Provincia.Buenos_Aires,
            "a",
            "b",
            "c",
            "d",
            1
        ),
        "apache inc",
        Tipo.EMPRESA,
        new Clasificacion("dasdsa")
    );
    org.agregarContacto("+NUMERO", "MAIL@frba.utn.edu.ar");
    agregarOrganizacion(org);
    */
    //FIN USO PRUEBA

    difundirNotificacion(new Notificacion("Mi Impacto Ambiental - Actualización de Guía de Recomendaciones", "https://miimpactoambiental-dds.herokuapp.com/recomendaciones"));
  }

}
