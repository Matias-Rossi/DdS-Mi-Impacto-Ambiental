package domain.security.password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestValidadorContraseniaFrecuente {

  @Test
  public void contraseniaValida() {
    ValidadorContraseniaFrecuente vcf = new ValidadorContraseniaFrecuente();  //Hacer uno unico para los 2 tests?
    assertTrue(vcf.validar("contraseniaParaDiseñoDeSistemas"));
  }

  @Test
  public void contraseniaInvalida() {
    ValidadorContraseniaFrecuente vcf = new ValidadorContraseniaFrecuente();
    assertFalse(vcf.validar("johnny1"));
  }
}
