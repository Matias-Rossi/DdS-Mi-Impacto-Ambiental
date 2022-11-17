package impacto_ambiental.models.entities.security.password;

public interface ValidadorCriterio {
  /**
  * Retorna true si el validador considera valida a la contrasenia introducida
  * @param contrasenia a validar
  * @return true si la contrasenia es valida, false si no lo es u ocurrio algun error
  */
  boolean validar(String contrasenia);
}
