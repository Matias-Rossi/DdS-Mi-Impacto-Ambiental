package domain.security.password;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import impacto_ambiental.models.entities.security.password.ValidadorContrasenia;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidadorContrasenia {
  private static ValidadorContrasenia vc;

  @BeforeEach
  public void init() {
    vc = new ValidadorContrasenia();
  }

  @Test
  @DisplayName("Contrasenia es válida")
  public void contraseniaValida() {
    assertTrue(vc.validar("disenioDeSistemas13", ValidadorContrasenia.completo));
  }

  @Test
  @DisplayName("Contrasenia no tiene mayúscula")
  public void contraseniaSinMayuscula() {
    assertFalse(vc.validar("disenodesistemas13", ValidadorContrasenia.completo));
  }

  @Test
  @DisplayName("Contrasenia no tiene minúscula")
  public void contraseniaSinMinuscula() {
    assertFalse(vc.validar("DISENIODESISTEMAS13", ValidadorContrasenia.completo));
  }

  @Test
  @DisplayName("Contrasenia no tiene número")
  public void contraseniaSinNumero() {
    assertFalse(vc.validar("disenioDeSistemas", ValidadorContrasenia.completo));
  }

  @Test
  @DisplayName("Contrasenia es muy corta")
  public void contraseniaCorta() {
    assertFalse(vc.validar("aB1", ValidadorContrasenia.completo));
  }


}
