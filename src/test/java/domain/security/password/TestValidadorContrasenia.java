package domain.security.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidadorContrasenia {

  @Test
  public void contraseniaValida() {
    ValidadorContrasenia vc = new ValidadorContrasenia();
    assertTrue(vc.esContraseniaValida("disenioDeSistemas13"));
  }

  @Test
  public void contraseniaSinMayuscula() {
    ValidadorContrasenia vc = new ValidadorContrasenia();
    assertFalse(vc.esContraseniaValida("disenodesistemas13"));
  }

  @Test
  public void contraseniaSinMinuscula() {
    ValidadorContrasenia vc = new ValidadorContrasenia();
    assertFalse(vc.esContraseniaValida("DISENIODESISTEMAS13"));
  }

  @Test
  public void contraseniaSinNumero() {
    ValidadorContrasenia vc = new ValidadorContrasenia();
    assertFalse(vc.esContraseniaValida("disenioDeSistemas"));
  }

  @Test
  public void contraseniaCorta() {
    ValidadorContrasenia vc = new ValidadorContrasenia();
    assertFalse(vc.esContraseniaValida("aB1"));
  }


}
