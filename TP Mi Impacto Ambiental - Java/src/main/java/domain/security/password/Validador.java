package domain.security.password;

abstract public class Validador {
  /**
   * Retorna un si el validador considera valida a la contrasenia introducida
   * @param contrasenia a validar
   * @return true si la contrasenia es valida, false si no lo es u ocurrio algun error
   */
  abstract boolean validar(String contrasenia);
}
