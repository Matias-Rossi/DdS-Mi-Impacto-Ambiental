package impacto_ambiental.models.entities.security.password;

public class ValidadorCriterioMayuscula extends ValidadorCriterioDecorator {

    public ValidadorCriterioMayuscula(ValidadorCriterio nuevoCriterio) {
      super(nuevoCriterio);
    }

    @Override
    public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isUpperCase(contrasenia.charAt(i))){
        return tempValidador.validar(contrasenia);
      }
    }
      System.out.println("La contrasenia debe una contener una letra mayuscula");
      tempValidador.validar(contrasenia);
      return false;
    }
  
}
