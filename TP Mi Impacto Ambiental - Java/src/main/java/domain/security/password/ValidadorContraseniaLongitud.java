package domain.security.password;

public class ValidadorContraseniaLongitud extends Validador{
  private int longitudMinima = 8;
  @Override
  public boolean validar(String contrasenia) {
    return contrasenia.length() >= longitudMinima;
  }
}
