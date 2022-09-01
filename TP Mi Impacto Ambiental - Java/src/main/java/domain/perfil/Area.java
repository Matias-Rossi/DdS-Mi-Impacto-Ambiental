package domain.perfil;

import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "areas")
public class Area extends EntidadPersistente {
    @Getter
    @Column(name = "nombre")
    public String nombre;
    @ManyToOne
    @JoinColumn(name = "organizaciones_id", referencedColumnName = "id")
    private Organizacion organizacion;
    /*
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Miembro> miembros = new ArrayList<>();
    @Transient
    private List<Miembro> miembrosPendientes = new ArrayList<>();
*/
    @OneToMany
    private  List<Solicitud> solicitudes = new ArrayList<>();

    public Area(String nombreArea, Organizacion nombreOrganizacion) {
        this.nombre = nombreArea;
        this.organizacion = nombreOrganizacion;
    }

    public Area() {

    }

    public Organizacion getOrganizacion() {
        return this.organizacion;
    }

    public void gestionarMiembrosPendientes(Solicitud solicitud, SolicitudEstado estado) {
        solicitud.setEstado(estado);
    }

    public void agregarAMiembroPendiente(Solicitud solicitud){

        this.solicitudes.add(solicitud);
    }

    public double calcularHCporMiembro(Integer anio,Integer mes){
        return this.calcularHC(anio,mes)/this.miembrosActuales().size();
    }

    private List<Solicitud> miembrosActuales(){
        return this.solicitudes.stream().filter(solicitud -> solicitud.getEstado() == SolicitudEstado.ACEPTADA).collect(Collectors.toList());
    }
    public double calcularHC(Integer anio, Integer mes){

        List<Double> mapped = solicitudes.stream().map(e->e.calcularHC(anio,mes,this.organizacion)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }

}
