package impacto_ambiental.models.entities.calculadorHC;
import impacto_ambiental.models.repositorios.RepositorioFactorDeEmision;

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

    public double calcularHC(FactorDeEmision factorDeEmision,Double valorDatoDeActividad){
        if (factorDeEmision == null) return 0;
            else return factorDeEmision.getFactorEmision() * valorDatoDeActividad;
    }

    public static FactorDeEmision devolverFactorDeEmision(DatoDeActividad datoDeActividad){
        //TODO: No veo como esto es responsabilidad del CalculadorDeHC
        TipoConsumoDA consumo = datoDeActividad.getTipoConsumo();
        TipoActividadDA actividad = datoDeActividad.getTipoActividad();
        RepositorioFactorDeEmision repositorioFactorDeEmision = new RepositorioFactorDeEmision();
        //FactorDeEmision factor =(FactorDeEmision) EntityManagerHelper.createQuery("from FactorDeEmision where tipoConsumo = '"+consumo+"' and tipoActividad = '"+actividad+"'");
        FactorDeEmision factor = repositorioFactorDeEmision.buscarSegunDatoDeActividad(datoDeActividad);
        System.out.println("LLEGUE ACAAAA");System.out.println("LLEGUE ACAAAA");
        return factor;
    }
    /*
    public void nuevoFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad, double factor) {
            factoresDeEmision.add(new FactorDeEmision(actividad,consumo,factor));
    }
    */

}

