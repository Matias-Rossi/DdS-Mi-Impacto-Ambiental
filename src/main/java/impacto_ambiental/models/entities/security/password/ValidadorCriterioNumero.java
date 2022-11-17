package impacto_ambiental.models.entities.security.password;

public class ValidadorCriterioNumero extends ValidadorCriterioDecorator {

    public ValidadorCriterioNumero(ValidadorCriterio nuevoCriterio) {
        super(nuevoCriterio);
    }

    @Override
    public boolean validar(String contrasenia) {
    for (int i=0;i<contrasenia.length(); i++)
    {
      if (Character.isDigit(contrasenia.charAt(i))){
        return tempValidador.validar(contrasenia);
      }
    }
      System.out.println("La contrasenia debe una contener un numero");
      tempValidador.validar(contrasenia);
      return false;
    }
  
}
