package domain.reportes;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Organizacion;

public class Reportes {
    TipoActividadDA tipoActividad;
    TipoConsumoDA tipoConsumo;
    Integer anio;
    Periodo periodo;
    Double huellaDeCarbono;
    Organizacion organizacion;
    public Reportes(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, Integer anio, Periodo periodo, Double huellaDeCarbono, Organizacion organizacion){
        this.tipoActividad = tipoActividad;
        this.tipoConsumo = tipoConsumo;
        this.anio = anio;
        this.periodo = periodo;
        this.huellaDeCarbono = huellaDeCarbono;
        this.organizacion = organizacion;
        organizacion.agregarReporte(this);
    }
}
