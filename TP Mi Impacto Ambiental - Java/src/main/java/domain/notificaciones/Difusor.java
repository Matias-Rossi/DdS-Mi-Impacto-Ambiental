package domain.notificaciones;

import domain.importadorExcel.ApachePOI;
import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.ArrayList;
import java.util.List;

public class Difusor {
  private List<Organizacion> organizaciones = new ArrayList<Organizacion>();
  private GestorNotificaciones gestorNotificaciones;

  public Difusor(GestorNotificaciones gestorNotificaciones) {
    this.gestorNotificaciones = gestorNotificaciones;
  }

  public void agregarOrganizacion(Organizacion organizacion) {
    this.organizaciones.add(organizacion);
  }

  public void difundirNotificacion(Notificacion notificacion) {
    System.out.println("Difundiendo notificación a organizaciones...");
    this.organizaciones.forEach(organizacion -> {
      organizacion.getContactos().forEach(contacto -> {
        System.out.println("Enviando notificacion");
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

    difundirNotificacion(new Notificacion("Mi Impacto Ambiental - Actualización de Guía de Recomendaciones", "<Enlace al contenido>"));
  }

}
