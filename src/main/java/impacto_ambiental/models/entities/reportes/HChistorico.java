package impacto_ambiental.models.entities.reportes;

import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "hcHistorico")
public class HChistorico extends EntidadPersistente {

    @Getter
    @ManyToOne
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    Area area;

    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    Miembro miembro;

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
    @Getter
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

    public HChistorico(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, Integer anio, Periodo periodo, Double huellaDeCarbono, Organizacion organizacion, Miembro miembro){

        Area area1 =organizacion.getAreas().stream().filter(ar->ar.tieneMiembro(miembro)).collect(Collectors.toList()).get(0);
        System.out.println("ESTA ES MI AREA");
        System.out.println(area1.nombre);


        this.tipoActividad = tipoActividad;
        this.tipoConsumo = tipoConsumo;
        this.anio = anio;
        this.periodo = periodo;
        this.huellaDeCarbono = huellaDeCarbono;
        this.organizacion = organizacion;
        this.miembro = miembro;
        this.area = area1;
        organizacion.agregarReporte(this);
        miembro.agregarHC(this);
    }

    public HChistorico() {

    }

    public Boolean comparar(Area area){
        if(this.area!=null)return this.area.equals(area);
        return false;
    }
    public Double getMomento(){
        return this.anio+(Periodo.toInteger(this.periodo) *0.01);
    }
}
