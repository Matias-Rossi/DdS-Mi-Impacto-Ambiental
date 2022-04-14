package domain.security.password;

import java.io.*;

public class ValidadorContraseniaFrecuente extends Validador {
  protected String PATH_A_CONTRASENIAS = "src/main/java/domain/security/password/10k-most-common.txt";

  @Override
  public boolean validar(String contrasenia) {

    //Intenta abrir archivo de contrasenias, si falla retorna false
    FileInputStream fstream;
    try {
      fstream = new FileInputStream(PATH_A_CONTRASENIAS);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return false;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    //Lectura linea a linea de contrasenias del archivo especificado en PATH_A_CONTRASENIAS
    String linea;
    while(true) {
      try {
        if ((linea = br.readLine()) != null) {
          //Buscando coincidencias
          if (linea.equals(contrasenia))
            return false;
        }
        //Fin de archivo
        else
          return true;
      }
      //Tratar excepciones causadas por entrada/salida
      catch (IOException e) {
        e.printStackTrace();
        return false;
      }

    }
  }



}
