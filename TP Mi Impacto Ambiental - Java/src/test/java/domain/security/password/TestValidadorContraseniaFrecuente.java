package domain.security.password;

import proservices.models.entities.security.password.ValidadorContrasenia;
import proservices.models.entities.security.password.ValidadorCriterio;
import proservices.models.entities.security.password.ValidadorCriterioBase;
import proservices.models.entities.security.password.ValidadorCriterioFrecuente;
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
