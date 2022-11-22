package impacto_ambiental.models.entities.servicios.twilio;

import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.sendgrid.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import impacto_ambiental.models.entities.notificaciones.Contacto;
import impacto_ambiental.models.entities.notificaciones.GestorNotificaciones;
import impacto_ambiental.models.entities.notificaciones.Notificacion;
import impacto_ambiental.models.entities.notificaciones.PreferenciasContacto;

public class ServicioTwilio implements GestorNotificaciones {
  public static String ACCOUNT_SID;

  public static String AUTH_TOKEN;
  public static String SENDGRID_API_KEY;
  public static String EMAIL_FROM;


  public ServicioTwilio() {
    Properties prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("tokens.properties");
    try {
      prop.load(stream);
      AUTH_TOKEN = prop.getProperty("twilioToken");
      ACCOUNT_SID = prop.getProperty("twilioAccountSid");
      SENDGRID_API_KEY = prop.getProperty("sendGridToken");
      EMAIL_FROM = prop.getProperty("emailTestsFrom");
    } catch(IOException e) {
      AUTH_TOKEN = "";
      ACCOUNT_SID = "";
      SENDGRID_API_KEY = "";
      EMAIL_FROM = "";
    }
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
  }


  public Boolean enviarWhatsapp(String telefono, String mensaje) {
    System.out.println("##### Enviando SMS a " + telefono);
    Message message = Message.creator(
            //TODO actualmente envía SMS, para enviar WhatsApp agregar "whatsapp:" antes del número
            new com.twilio.type.PhoneNumber("" + telefono),
            new com.twilio.type.PhoneNumber("+12057510367"),
            mensaje)
        .create();
    return message.getErrorCode() == null;
  }
  public Boolean enviarEmail(String destinatario, String asunto, String contenido) {
    System.out.println("##### Enviando mail a " + destinatario);
    Email from = new Email(EMAIL_FROM);
    String subject = asunto;
    Email to = new Email(destinatario);
    Content content = new Content("text/plain", contenido);
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid(SENDGRID_API_KEY);
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
      return response.getStatusCode() < 300;
    } catch (IOException ex) {
      System.out.println("Error al enviar email");
      return false;
    }
  }

  public Boolean enviarNotificacion(Contacto contacto, Notificacion notificacion) {
    //hay que mejorar como esta planteado esto
    if (contacto.getPreferenciasContacto() == PreferenciasContacto.WHATSAPP) {
      return enviarWhatsapp(contacto.getTelefono(), notificacion.formatoWhatsapp());
    } else if (contacto.getPreferenciasContacto() == PreferenciasContacto.EMAIL) {
      return enviarEmail(contacto.getEmail(), notificacion.getAsunto(), notificacion.getContenido());
    } else {
      Boolean exitoWpp = enviarWhatsapp(contacto.getTelefono(), notificacion.formatoWhatsapp());
      Boolean exitoEmail = enviarEmail(contacto.getEmail(), notificacion.getAsunto(), notificacion.getContenido());
      return exitoEmail && exitoWpp;
    }
  }
}
