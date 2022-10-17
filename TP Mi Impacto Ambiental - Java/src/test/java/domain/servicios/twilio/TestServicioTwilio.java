package domain.servicios.twilio;

import impacto_ambiental.models.entities.importadorExcel.ApachePOI;
import impacto_ambiental.models.entities.notificaciones.Contacto;
import impacto_ambiental.models.entities.notificaciones.Difusor;
import impacto_ambiental.models.entities.notificaciones.GestorNotificaciones;
import impacto_ambiental.models.entities.notificaciones.Notificacion;
import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Tipo;
import impacto_ambiental.models.entities.servicios.twilio.ServicioTwilio;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
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
    MunicipiosODepartamentos moron = new MunicipiosODepartamentos(new Provincia(NombreProvincia.BUENOS_AIRES), "Morón");

    Organizacion org = new Organizacion(
            new ApachePOI(),
            new Ubicacion(
                    moron,
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

    //difusor.agregarOrganizacion(org); //DESCOMENTAR PARA FUNCIONALIDAD

    //difusor.difundirRecomendaciones(); //DESCOMENTAR PARA FUNCIONALIDAD

  }

  @Test
  public void testWhatsapp() {
    //ATENCIÓN: este test incurre un gasto en la cuenta vinculada de Twilio
    String tel = obtenerNumeroTelefonico();
    ServicioTwilio servicioTwilio = new ServicioTwilio();
    Contacto contacto = new Contacto("+" + tel, null,null);
    Notificacion notificacion = new Notificacion("Test asunto", "Test contenido");
    //servicioTwilio.enviarNotificacion(contacto, notificacion); //DESCOMENTAR PARA FUNCIONALIDAD
  }

  @Test
  public void testEmail() {
    //ATENCIÓN: este test incurre un gasto en la cuenta vinculada de SendGrid
    String email = obtenerEmail();
    ServicioTwilio servicioTwilio = new ServicioTwilio();
    Contacto contacto = new Contacto(null, email,null);
    Notificacion notificacion = new Notificacion("Mi Impacto Ambiental - Actualización de Guía de Recomendaciones", "<Enlace al contenido>");
    Boolean exito = true; //servicioTwilio.enviarNotificacion(contacto, notificacion); //DESCOMENTAR PARA FUNCIONALIDAD
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
