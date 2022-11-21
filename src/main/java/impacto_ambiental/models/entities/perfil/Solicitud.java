package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "solicitudes")
public class Solicitud extends EntidadPersistente {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private SolicitudEstado estado;

    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;
    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "area_id", referencedColumnName = "id")
    private Area area;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Miembro getMiembro() {
        return miembro;
    }
    public SolicitudEstado getEstado() {
        return estado;
    }
    public void setEstado(SolicitudEstado estado) {
        this.estado = estado;
    }
    public  void imprimir(){System.out.println(estado);}


    public Solicitud(){}
    public Solicitud(Miembro miembro,Area area) {
        this.area = area;
        this.miembro = miembro;
        this.estado = SolicitudEstado.PENDIENTE;
        area.agregarAMiembroPendiente(this);
    }

    public void calcularHC() {
        if(estado== SolicitudEstado.ACEPTADA){
            miembro.calcularHC(this.area.getOrganizacion());
        }
    }
}
