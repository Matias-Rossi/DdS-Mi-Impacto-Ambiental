package impacto_ambiental.models.entities.security.password;

public class ValidadorCriterioMinuscula extends ValidadorCriterioDecorator {

    public ValidadorCriterioMinuscula(ValidadorCriterio nuevoCriterio) {
      super(nuevoCriterio);
    }

    @Override
    public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isLowerCase(contrasenia.charAt(i))){
        return tempValidador.validar(contrasenia);
      }
    }
      System.out.println("La contrasenia debe una contener una letra minuscula");
      tempValidador.validar(contrasenia);
      return false;
    }
  
}
