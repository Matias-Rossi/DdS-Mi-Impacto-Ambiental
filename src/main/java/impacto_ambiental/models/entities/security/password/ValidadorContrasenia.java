package impacto_ambiental.models.entities.security.password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ValidadorContrasenia {
  static public ValidadorCriterioDecorator completo = new ValidadorCriterioMayuscula(
      new ValidadorCriterioLongitud(
          new ValidadorCriterioFrecuente(
              new ValidadorCriterioNumero(
                  new ValidadorCriterioMinuscula(
                      new ValidadorCriterioBase()
                  )
              )
          )
      )
  );

  //TODO Para fines de testing (desp sacar)
  /*
  public static void main(String[] args) throws IOException {
    System.out.print("Ingrese una contrasenia: ");
    BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    String contrasenia = lector.readLine();

    final boolean resultadoValidaciones = completo.validar(contrasenia);

    if(resultadoValidaciones) {
      System.out.println("Contrasenia valida");
    } else {
      System.out.println("Contrasenia invalida");
    }
        
  }
*/
  public boolean validar(String contrasenia, ValidadorCriterio criterio){
    final boolean resultadoValidaciones = criterio.validar(contrasenia);

    if(resultadoValidaciones) {
      System.out.println("Contrasenia valida");
    } else {
      System.out.println("Contrasenia invalida");
    }

    return resultadoValidaciones;
  }
}
