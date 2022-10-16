package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "solicitudes")
public class Solicitud extends EntidadPersistente {

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private SolicitudEstado estado;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

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
    void setEstado(SolicitudEstado estado) {
        this.estado = estado;
    }


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
