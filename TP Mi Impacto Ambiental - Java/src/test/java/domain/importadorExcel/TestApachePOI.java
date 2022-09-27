package domain.importadorExcel;


import domain.perfil.Clasificacion;
import domain.perfil.Importador;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.persistenceExtend.repositorios.RepositorioProvincias;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.NombreProvincia;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApachePOI {

  @Test
  @DisplayName("Apache POI carga mediciones desde Excel")
  public void testApachePoi() {
    Importador importadorApache = new ApachePOI();
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
    Ubicacion ubicacionTest = new Ubicacion(
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires), "Chivilcoy"),
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
