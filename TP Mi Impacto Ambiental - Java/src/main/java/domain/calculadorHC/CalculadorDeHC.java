package domain.calculadorHC;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.repositorios.RepositorioFactorDeEmision;

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
        TipoConsumoDA consumo = datoDeActividad.getTipoConsumoDA();
        TipoActividadDA actividad = datoDeActividad.getTipoActividadDA();
        RepositorioFactorDeEmision repositorioFactorDeEmision = new RepositorioFactorDeEmision();
        //FactorDeEmision factor =(FactorDeEmision) EntityManagerHelper.createQuery("from FactorDeEmision where tipoConsumo = '"+consumo+"' and tipoActividad = '"+actividad+"'");
        FactorDeEmision factor = repositorioFactorDeEmision.buscarSegunDatoDeActividad(datoDeActividad);
        return factor;
    }
    /*
    public void nuevoFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad, double factor) {
            factoresDeEmision.add(new FactorDeEmision(actividad,consumo,factor));
    }
    */

}

