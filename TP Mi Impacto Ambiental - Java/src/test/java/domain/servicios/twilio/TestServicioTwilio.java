package domain.servicios.twilio;

import domain.importadorExcel.ApachePOI;
import domain.notificaciones.*;
import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestServicioTwilio {

  @Test
  public void testDifusor() {
    //ATENCIÓN: este test incurre un gasto en la cuenta vinculada de Twilio
    GestorNotificaciones gestorNotificaciones = new ServicioTwilio();
    Difusor difusor = new Difusor(gestorNotificaciones);

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
    org.agregarContacto("+" + obtenerNumeroTelefonico(), obtenerEmail());

    difusor.agregarOrganizacion(org);

    difusor.difundirRecomendaciones();

  }

  @Test
  public void testWhatsapp() {
    //ATENCIÓN: este test incurre un gasto en la cuenta vinculada de Twilio
    String tel = obtenerNumeroTelefonico();
    ServicioTwilio servicioTwilio = new ServicioTwilio();
    Contacto contacto = new Contacto("+" + tel, null);
    Notificacion notificacion = new Notificacion("Test asunto", "Test contenido");
    servicioTwilio.enviarNotificacion(contacto, notificacion);
    assert(true);
  }

  @Test
  public void testEmail() {
    //ATENCIÓN: este test incurre un gasto en la cuenta vinculada de SendGrid
    String email = obtenerEmail();
    ServicioTwilio servicioTwilio = new ServicioTwilio();
    Contacto contacto = new Contacto(null, email);
    Notificacion notificacion = new Notificacion("Mi Impacto Ambiental - Actualización de Guía de Recomendaciones", "<Enlace al contenido>");
    Boolean exito = servicioTwilio.enviarNotificacion(contacto, notificacion);
    assert(exito);
  }

  public String obtenerNumeroTelefonico(){
    Properties prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("tokens.properties");
    try {
      prop.load(stream);
      return prop.getProperty("numeroCelularTests");
    } catch(IOException e) {
      return "";
    }
  }

  public String obtenerEmail(){
    Properties prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("tokens.properties");
    try {
      prop.load(stream);
      return prop.getProperty("emailTestsTo");
    } catch(IOException e) {
      return "";
    }
  }

}
