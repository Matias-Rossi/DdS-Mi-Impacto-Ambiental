package main.java.domain.security.password;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidadorCriterioFrecuente extends ValidadorCriterioDecorator {


  public ValidadorCriterioFrecuente(ValidadorCriterio nuevoCriterio) {
    super(nuevoCriterio);
  }

  @Override
  public boolean validar(String contrasenia) {
    
    //Intenta abrir archivo de contrasenias, si falla retorna false
    FileInputStream fstream;
    try {
      String PATH_A_CONTRASENIAS = "TP Mi Impacto Ambiental - Java/src/main/java/domain/security/password/10k-most-common.txt";
      fstream = new FileInputStream(PATH_A_CONTRASENIAS);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.out.println("Error comprobando fortaleza de la contrasenia");
      tempValidador.validar(contrasenia);
      return false;
    }
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    //Lectura linea a linea de contrasenias del archivo especificado en PATH_A_CONTRASENIAS
    String linea;
    while(true) {
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
