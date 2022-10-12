package proservices.models.entities.reportes;

import proservices.models.entities.calculadorHC.TipoActividadDA;
import proservices.models.entities.calculadorHC.TipoConsumoDA;
import proservices.models.entities.perfil.Organizacion;
import proservices.models.entities.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "hcHistorico")
public class HChistorico extends EntidadPersistente {
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_actividad")
    TipoActividadDA tipoActividad;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_consumo")
    TipoConsumoDA tipoConsumo;
    @Getter
    @Column(name = "anio")
    Integer anio;
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "periodo")
    Periodo periodo;
    @Getter
    @Column(name = "huella_de_carbono")
    Double huellaDeCarbono;
    @ManyToOne
    @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    Organizacion organizacion;
    public HChistorico(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, Integer anio, Periodo periodo, Double huellaDeCarbono, Organizacion organizacion){
        this.tipoActividad = tipoActividad;
        this.tipoConsumo = tipoConsumo;
        this.anio = anio;
        this.periodo = periodo;
        this.huellaDeCarbono = huellaDeCarbono;
        this.organizacion = organizacion;
        organizacion.agregarReporte(this);
    }

    public HChistorico() {

    }
    public Double getMomento(){
        return this.anio+(Periodo.toInteger(this.periodo) *0.01);
    }
}
