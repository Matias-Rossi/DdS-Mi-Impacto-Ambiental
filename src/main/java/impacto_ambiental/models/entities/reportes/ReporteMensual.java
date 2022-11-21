package impacto_ambiental.models.entities.reportes;

import lombok.Getter;

public class ReporteMensual {
    @Getter
    public Periodo periodo;
    @Getter
    public Integer anio;
    @Getter
    public Double hc;
    public ReporteMensual(Periodo periodo, Integer anio) {
        this.periodo = periodo;
        this.anio = anio;
        this.hc = 0.0;
    }
    public void sumar(Double hc) {
        this.hc += hc;
    }
}
