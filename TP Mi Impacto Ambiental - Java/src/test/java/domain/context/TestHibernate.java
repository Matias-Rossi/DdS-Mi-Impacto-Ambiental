package domain.context;

import domain.calculadorHC.*;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHibernate {
    @Test
    public void persisting() {
        FactorDeEmision factorDeEmision = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
        EntityManagerHelper.delete(factorDeEmision);
        EntityManagerHelper.persist(factorDeEmision);
    }
    @Test
    public void hidratarFactorDeEmision(){
        DatoDeActividad dato = new DatoDeActividad(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,0.5);
        FactorDeEmision fac =CalculadorDeHC.getInstance().devolverFactorDeEmision(dato);
        assertEquals(0.5,fac.getFactorEmision());
    }
    @Test
    public void pruebasHidratacion(){

    }
}
