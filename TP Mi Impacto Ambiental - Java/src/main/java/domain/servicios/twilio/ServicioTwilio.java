package domain.servicios.twilio;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.math.BigDecimal;
import java.util.Properties;

import domain.notificaciones.Contacto;
import domain.notificaciones.Notificacion;
import domain.notificaciones.PreferenciasContacto;

public class ServicioTwilio {
  public static String ACCOUNT_SID = "ACceaa4ede1fcae384e661b5b2327c60b6";

  public static String AUTH_TOKEN;


  ServicioTwilio() {
    Properties prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("tokens.properties");
    try {
      prop.load(stream);
      AUTH_TOKEN = prop.getProperty("twilioToken");
    } catch(IOException e) {
      AUTH_TOKEN = "";
    }
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }


  public  void enviarWhatsapp(String telefono, String mensaje) {
    Message message = Message.creator(
            new com.twilio.type.PhoneNumber("whatsapp:" + telefono),
            new com.twilio.type.PhoneNumber("whatsapp:+17406392786"),
            mensaje)
        .create();
  }
  public  void enviarEmail(String email, String asunto, String contenido) {
    //TODO
  }

  public void enviarNotificacion(Contacto contacto, Notificacion notificacion) {
    //TODO hay que mejorar como esta planteado esto
    if (contacto.getPreferenciasContacto() == PreferenciasContacto.WHATSAPP) {
      enviarWhatsapp(contacto.getTelefono(), notificacion.formatoWhatsapp());
    } else if (contacto.getPreferenciasContacto() == PreferenciasContacto.EMAIL) {
      enviarEmail(contacto.getEmail(), notificacion.getAsunto(), notificacion.getContenido());
    } else {
      enviarWhatsapp(contacto.getTelefono(), notificacion.formatoWhatsapp());
      enviarEmail(contacto.getEmail(), notificacion.getAsunto(), notificacion.getContenido());
    }
  }
}
