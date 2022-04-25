package domain.security.password;

public class ValidadorContraseniaConMinuscula extends Validador {
  @Override
  public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isLowerCase(contrasenia.charAt(i))){
        return true;
      }
    }
    return false;
  }
}
