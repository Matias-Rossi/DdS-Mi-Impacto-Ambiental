package domain.security.password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    assertTrue(vc.esContraseniaValida("disenioDeSistemas13"));
  }

  @Test
  @DisplayName("Contrasenia no tiene mayúscula")
  public void contraseniaSinMayuscula() {
    assertFalse(vc.esContraseniaValida("disenodesistemas13"));
  }

  @Test
  @DisplayName("Contrasenia no tiene minúscula")
  public void contraseniaSinMinuscula() {
    assertFalse(vc.esContraseniaValida("DISENIODESISTEMAS13"));
  }

  @Test
  @DisplayName("Contrasenia no tiene número")
  public void contraseniaSinNumero() {
    assertFalse(vc.esContraseniaValida("disenioDeSistemas"));
  }

  @Test
  @DisplayName("Contrasenia es muy corta")
  public void contraseniaCorta() {
    assertFalse(vc.esContraseniaValida("aB1"));
  }

}
