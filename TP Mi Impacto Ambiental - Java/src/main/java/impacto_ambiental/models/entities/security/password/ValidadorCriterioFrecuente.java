package impacto_ambiental.models.entities.security.password;

import java.io.*;

public class ValidadorCriterioFrecuente extends ValidadorCriterioDecorator {


  public ValidadorCriterioFrecuente(ValidadorCriterio nuevoCriterio) {
    super(nuevoCriterio);
  }

  @Override
  public boolean validar(String contrasenia) {

    //Intenta abrir archivo de contrasenias, si falla retorna false
    FileInputStream fstream;
    String PATH_A_CONTRASENIAS = null; //TODO: Pasarlo al .properties
    try {
      PATH_A_CONTRASENIAS = "/10k-most-common.txt";
      fstream = new FileInputStream(PATH_A_CONTRASENIAS);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error comprobando fortaleza de la contrasenia");

      File f = new File(PATH_A_CONTRASENIAS);
      if(!f.exists()) {
        System.out.println("El archivo no existe");
        return true; //TODO Sacar!
      } else {
        if(!f.canRead()) {
          System.out.println("El archivo no puede ser leido");
        }
      }

      tempValidador.validar(contrasenia);
      return false;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    //Lectura linea a linea de contrasenias del archivo especificado en PATH_A_CONTRASENIAS
    String linea;
    while (true) {
      try {
        if ((linea = br.readLine()) != null) {
          //Buscando coincidencias
          if (linea.equals(contrasenia)) {
            System.out.println("La contrasenia es muy debil");
            br.close();
            tempValidador.validar(contrasenia);
            return false;
          }
        }
        //Fin de archivo
        else {
          br.close();
          return tempValidador.validar(contrasenia);
        }
      }
      //Tratar excepciones causadas por entrada/salida
      catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error comprobando fortaleza de la contrasenia");
        tempValidador.validar(contrasenia);
        return false;
      }

    }
  }
  
}
