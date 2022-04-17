package domain.security.password;

public class ValidadorContraseniaLongitud extends Validador{
  @Override
  public boolean validar(String contrasenia) {
    return contrasenia.length() >= 8;
  }
}
