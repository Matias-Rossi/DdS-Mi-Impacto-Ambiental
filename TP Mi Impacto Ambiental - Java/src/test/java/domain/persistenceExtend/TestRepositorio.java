package domain.persistenceExtend;

import proservices.models.entities.calculadorHC.FactorDeEmision;
import proservices.models.entities.calculadorHC.TipoActividadDA;
import proservices.models.entities.calculadorHC.TipoConsumoDA;
import proservices.models.repositorios.RepositorioFactorDeEmision;
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
