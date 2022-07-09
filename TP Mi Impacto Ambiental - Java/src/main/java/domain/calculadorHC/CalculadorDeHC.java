package domain.calculadorHC;
import java.util.List;

public class CalculadorDeHC {

    private List<FactorDeEmision> factoresDeEmision;

    public static double calcularHC(DatoDeActividad actividades){
        FactorDeEmision factorDeEmision = this.asignarFactorDeEmision(actividades.getTipoconsumoDA(),actividades.getTipoActividadDA())

        return 1.0;
    }

    public FactorDeEmision asignarFactorDeEmision(TipoConsumoDA consumo, TipoActividadDA actividad) {
        return factoresDeEmision.stream()
                .filter(factor -> factor.getTipoConsumo().equals(consumo) && factor.getTipoActividad().equals(actividad))
                .findFirst()
                .orElse(null);

    }

    }


