package domain.security.password;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidadorContrasenia {
  private List<Validador> validadores = Arrays.asList(
      new ValidadorContraseniaFrecuente(),
      new ValidadorContraseniaConMayuscula(),
      new ValidadorContraseniaConMinuscula(),
      new ValidadorContraseniaLongitud(),
      new ValidadorContraseniaConNumero()
  );

  public boolean esContraseniaValida(String contrasenia) {
    for(Validador val : validadores) {
      if(!val.validar(contrasenia)) return false;
    }
    return true;
  }


}
