package domain.calculadorHC;
import domain.persistenceExtend.EntityManagerHelper;

import java.util.ArrayList;
import java.util.List;

public final class CalculadorDeHC {

    private static CalculadorDeHC instance;

    public static CalculadorDeHC getInstance() {
        if (instance == null) {
            instance = new CalculadorDeHC();
        }
        return instance;
    }

    private  List<FactorDeEmision> factoresDeEmision = new ArrayList<>();

    public double calcularHC(FactorDeEmision factorDeEmision,Double valorDa){
        if (factorDeEmision == null) return 0;
            else return factorDeEmision.getFactorEmision() * valorDa;
    }

    public static FactorDeEmision devolverFactorDeEmision(DatoDeActividad datoDeActividad){
        TipoConsumoDA consumo = datoDeActividad.getTipoconsumoDA();
        TipoActividadDA actividad = datoDeActividad.getTipoActividadDA();
        FactorDeEmision factor =(FactorDeEmision) EntityManagerHelper.createQuery("from FactorDeEmision where tipoConsumo = '"+consumo+"' and tipoActividad = '"+actividad+"'");
        return factor;
    }
    /*
    public void nuevoFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad, double factor) {
            factoresDeEmision.add(new FactorDeEmision(actividad,consumo,factor));
    }
    */

}

