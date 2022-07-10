package domain.notificaciones;

public interface GestorNotificaciones {
  Boolean enviarWhatsapp(String telefono, String mensaje);
  Boolean enviarEmail(String destinatario, String asunto, String contenido);
  Boolean enviarNotificacion(Contacto contacto, Notificacion notificacion);
}
