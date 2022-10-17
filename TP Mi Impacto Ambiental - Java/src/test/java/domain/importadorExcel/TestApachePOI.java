package domain.importadorExcel;


import impacto_ambiental.models.entities.importadorExcel.ApachePOI;
import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Importador;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Tipo;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
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
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.BUENOS_AIRES), "Chivilcoy"),
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
