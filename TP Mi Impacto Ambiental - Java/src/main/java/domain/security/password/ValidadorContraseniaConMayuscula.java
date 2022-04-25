package domain.security.password;

public class ValidadorContraseniaConMayuscula extends Validador  {
  @Override
  public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isUpperCase(contrasenia.charAt(i))){
      return true;
    }
    }
    return false;
  }

}
