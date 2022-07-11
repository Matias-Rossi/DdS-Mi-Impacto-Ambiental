package domain.notificaciones;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestContacto {
  @Test
  public void testContactoPreferenciaTodos() {
    Contacto contacto = new Contacto("123456789", "x@gmail.com");
    assertEquals(PreferenciasContacto.TODOS, contacto.getPreferenciasContacto());
  }

  @Test
  public void testContactoPreferenciaEmail() {
    Contacto contacto = new Contacto(null, "x@gmail.com");
    assertEquals(PreferenciasContacto.EMAIL, contacto.getPreferenciasContacto());
  }

  @Test
  public void testContactoPreferenciaWhatsapp() {
    Contacto contacto = new Contacto("123456789", null);
    assertEquals(PreferenciasContacto.WHATSAPP, contacto.getPreferenciasContacto());
  }
}

