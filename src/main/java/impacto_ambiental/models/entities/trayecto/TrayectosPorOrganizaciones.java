package impacto_ambiental.models.entities.trayecto;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "trayectosPorOrganizaciones")
public class TrayectosPorOrganizaciones extends EntidadPersistente {
    @Getter
            @Setter
            @ManyToOne
            @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
    Organizacion organizacion;
  @Getter
  @Setter
  @ManyToOne
  @JoinColumn(name = "area_id", referencedColumnName = "id")
  Area area;

  @Getter
            @Setter
            @ManyToOne
            @JoinColumn(name = "trayecto_id", referencedColumnName = "id")
    Trayecto trayecto;
    @Getter
            @Setter
            @Column(name = "hc")
    Double hc;

    TrayectosPorOrganizaciones(Organizacion organizacion, Trayecto trayecto){
        this.organizacion = organizacion;
        this.trayecto = trayecto;
        this.hc = null;
    }

    public TrayectosPorOrganizaciones() {

    }
}
