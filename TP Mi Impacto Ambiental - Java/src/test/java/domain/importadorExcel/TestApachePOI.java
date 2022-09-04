package domain.importadorExcel;


import domain.calculadorHC.CalculadorDeHC;
import domain.perfil.Clasificacion;
import domain.perfil.Importador;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApachePOI {
  @Test
  @DisplayName("TEST APACHE POI")
  public void testApachePoi() {
    Importador importadorApache = new ApachePOI();
    Ubicacion ubicacionTest = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            123
    );
    Clasificacion clasificacionTest = new Clasificacion("dasdsa");
    Organizacion organizacionTest = new Organizacion(importadorApache, ubicacionTest, "testSA", Tipo.EMPRESA, clasificacionTest);
    organizacionTest.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx");
    assertTrue(organizacionTest.getActividadesCargadas().size() == 5);
  }

}
