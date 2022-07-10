package domain.notificaciones;

import domain.perfil.Organizacion;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import java.util.ArrayList;
import java.util.List;

public class Difusor implements Job {
  private List<Organizacion> organizaciones = new ArrayList<Organizacion>();
  private GestorNotificaciones gestorNotificaciones;

  public Difusor(GestorNotificaciones gestorNotificaciones) {
    this.gestorNotificaciones = gestorNotificaciones;
  }

  public void agregarOrganizacion(Organizacion organizacion) {
    this.organizaciones.add(organizacion);
  }

  public void difundirNotificacion(Notificacion notificacion) {
    this.organizaciones.forEach(organizacion -> {
      organizacion.getContactos().forEach(contacto -> {
        this.gestorNotificaciones.enviarNotificacion(contacto, notificacion);
      });
    });
  }

  public void difundirRecomendaciones() {
    difundirNotificacion(new Notificacion("Mi Impacto Ambiental - Actualización de Guía de Recomendaciones", "<Enlace al contenido>"));
  }


  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
    System.out.println("Difundiendo recomendaciones");
    difundirRecomendaciones();
  }
}
