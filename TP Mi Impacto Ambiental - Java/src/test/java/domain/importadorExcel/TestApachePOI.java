package domain.importadorExcel;


import domain.perfil.Importador;
import domain.perfil.Organizacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApachePOI {
  @Test
  @DisplayName("TEST APACHE POI")
  public void testApachePoi(){
    Importador importadorApache = new ApachePOI();
    Organizacion organizacionTest = new Organizacion(importadorApache);
    organizacionTest.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx");
    assertTrue(organizacionTest.getActividadesCargadas().size() == 5);
  }

}
