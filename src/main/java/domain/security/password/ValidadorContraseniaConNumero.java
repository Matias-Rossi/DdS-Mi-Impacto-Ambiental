package domain.security.password;

public class ValidadorContraseniaConNumero extends Validador {

  @Override
  public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isDigit(contrasenia.charAt(i))){
        return true;
      }
    }
    return false;
  }



}
