package proservices.models.entities.reportes;

import lombok.Getter;

public class ReporteMensual {
    @Getter
    Periodo periodo;
    @Getter
    Integer anio;
    @Getter
    Double hc;
    public ReporteMensual(Periodo periodo, Integer anio) {
        this.periodo = periodo;
        this.anio = anio;
        this.hc = 0.0;
    }
    public void sumar(Double hc) {
        this.hc += hc;
    }
}
