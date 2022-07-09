package domain.servicios.twilio;

import domain.notificaciones.Contacto;
import domain.notificaciones.Notificacion;
import org.junit.jupiter.api.Test;

public class TestServicioTwilio {
  @Test
  public void testWhatsapp() {
    ServicioTwilio servicioTwilio = new ServicioTwilio();
    Contacto contacto = new Contacto("+5491121903228", null);
    Notificacion notificacion = new Notificacion("Test asunto", "Test contenido");
    servicioTwilio.enviarNotificacion(contacto, notificacion);
    assert(true);
  }

}
