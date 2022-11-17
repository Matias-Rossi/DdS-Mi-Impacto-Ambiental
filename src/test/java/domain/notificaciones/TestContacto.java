package domain.notificaciones;

import org.junit.jupiter.api.Test;
import impacto_ambiental.models.entities.notificaciones.Contacto;
import impacto_ambiental.models.entities.notificaciones.PreferenciasContacto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestContacto {
  @Test
  public void testContactoPreferenciaTodos() {
    Contacto contacto = new Contacto("123456789", "x@gmail.com",null);
    assertEquals(PreferenciasContacto.TODOS, contacto.getPreferenciasContacto());
  }

  @Test
  public void testContactoPreferenciaEmail() {
    Contacto contacto = new Contacto(null, "x@gmail.com",null);
    assertEquals(PreferenciasContacto.EMAIL, contacto.getPreferenciasContacto());
  }

  @Test
  public void testContactoPreferenciaWhatsapp() {
    Contacto contacto = new Contacto("123456789", null,null);
    assertEquals(PreferenciasContacto.WHATSAPP, contacto.getPreferenciasContacto());
  }

  @Test
  public void testContactoNulo() {
    Exception excep = assertThrows(IllegalArgumentException.class, () -> {
      new Contacto(null, null,null);
    });
  }
}

