package domain.persistenceExtend;

import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.repositorios.RepositorioFactorDeEmision;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRepositorio {

  @Test
  @DisplayName("Repositorio puede guardar y retraer una entrada")
  public void testRepositorio() {
    RepositorioFactorDeEmision repositorio = new RepositorioFactorDeEmision();

    FactorDeEmision saliente = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
    repositorio.agregar(saliente);

    FactorDeEmision entrante = repositorio.buscar(saliente.getId());
    System.out.println(entrante == null? "Null" : "No null");
    assertEquals(saliente, entrante);
  }
}
