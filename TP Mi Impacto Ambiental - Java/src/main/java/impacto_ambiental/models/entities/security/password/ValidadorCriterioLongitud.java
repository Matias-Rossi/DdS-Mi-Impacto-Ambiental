package impacto_ambiental.models.entities.security.password;

public class ValidadorCriterioLongitud extends ValidadorCriterioDecorator {

  public ValidadorCriterioLongitud(ValidadorCriterio nuevoCriterio) {
    super(nuevoCriterio);
  }

  @Override
  public boolean validar(String contrasenia) {
    int LONGITUD_MINIMA = 8;
    if (contrasenia.length() >= LONGITUD_MINIMA) {
      return tempValidador.validar(contrasenia);
    } else {
      System.out.println("La contrasenia debe una contener al menos 8 caracteres");
      tempValidador.validar(contrasenia);
      return false;
    }
  }
  
}
