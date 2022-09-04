package domain.calculadorHC;
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

    public double calcularHC(DatoDeActividad actividad){
        FactorDeEmision factorDeEmision = devolverFactorDeEmision(actividad.getTipoconsumoDA(),actividad.getTipoActividadDA());
        if (factorDeEmision == null) return 0;
            else return factorDeEmision.getFactorEmision() * actividad.getDA();
    }

    public FactorDeEmision devolverFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad) {
        return factoresDeEmision.stream()
                .filter(factor -> factor.getTipoConsumo().equals(consumo) && factor.getTipoActividad().equals(actividad))
                .findFirst()
                .orElse(null);
    }
    public void nuevoFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad, double factor) {
            factoresDeEmision.add(new FactorDeEmision(actividad,consumo,factor));
    }
}


