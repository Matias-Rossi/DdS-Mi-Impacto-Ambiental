package domain.reportes;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "reportes")
public class Reportes extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad")
    TipoActividadDA tipoActividad;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_consumo")
    TipoConsumoDA tipoConsumo;
    @Column(name = "anio")
    Integer anio;
    @Enumerated(EnumType.STRING)
    @Column(name = "periodo")
    Periodo periodo;
    @Getter
    @Column(name = "huella_de_carbono")
    Double huellaDeCarbono;
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
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

    public Reportes() {

    }
}
