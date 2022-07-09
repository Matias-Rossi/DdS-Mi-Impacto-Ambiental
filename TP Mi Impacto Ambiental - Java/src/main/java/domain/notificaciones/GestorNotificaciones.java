package domain.notificaciones;

public interface GestorNotificaciones {
  void enviarWhatsapp(Notificacion notificacion, String telefono);
  void enviarEmail(Notificacion notificacion, String asunto, String contenido);
  void enviarNotificacion(Contacto contacto, Notificacion notificacion);
}
