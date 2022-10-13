package impacto_ambiental.models.entities.security.password;

public abstract class ValidadorCriterioDecorator implements ValidadorCriterio{
  protected ValidadorCriterio tempValidador;

  public ValidadorCriterioDecorator(ValidadorCriterio nuevoCriterio) {
    tempValidador = nuevoCriterio;
  }

  public boolean validar(String contrasenia) {
    return tempValidador.validar(contrasenia);
  }
}
