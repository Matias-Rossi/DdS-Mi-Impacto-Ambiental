package domain.notificaciones;

import domain.perfil.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class Difusor {
  private List<Organizacion> organizaciones = new ArrayList<Organizacion>();
  private GestorNotificaciones gestorNotificaciones; //TODO Agregar Twilio

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
}
