package test.java.domain.security.password;

import main.java.domain.security.password.ValidadorContrasenia;
import main.java.domain.security.password.ValidadorCriterio;
import main.java.domain.security.password.ValidadorCriterioBase;
import main.java.domain.security.password.ValidadorCriterioFrecuente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidadorContraseniaFrecuente {
  private final ValidadorCriterio vcf = new ValidadorCriterioFrecuente(new ValidadorCriterioBase());
  private final ValidadorContrasenia vc = new ValidadorContrasenia();

  @Test
  public void contraseniaValida() {
    assertTrue(vc.validar("contraseniaParaDiseñoDeSistemas", vcf));
  }

  @Test
  public void contraseniaInvalida() {
    assertFalse(vc.validar("johnny1", vcf));
  }
}
