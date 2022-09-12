package domain.perfil;

import domain.persistenceExtend.EntidadPersistente;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "miembros")
public class Miembro extends EntidadPersistente {

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private Ubicacion ubicacion;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_doc")
    private TipoDocumento tipoDocumento;
    @Column(name = "numero_doc")
    private Integer numeroDocumento;
    /*
    @ManyToMany
    private List<Area> areas = new ArrayList<Area>();
*/
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "miembro")
    public List<Solicitud> solicitudes = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "miembro")
    private List<Trayecto> trayectos = new ArrayList<Trayecto>();
    @Getter
    @ManyToMany
    @JoinTable(
            name = "tramos_a_aceptar_por_miembro",
            joinColumns = @JoinColumn(name = "miembros_id"),
            inverseJoinColumns = @JoinColumn(name = "tramos_id")
    )
    private List<Tramo> tramosCompartidosAAceptar = new ArrayList<Tramo>();

    public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento,Ubicacion ubicacion,String mail,String usuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.ubicacion=ubicacion;
        this.mail=mail;
        this.usuario=usuario;
    }

    public Miembro() {

    }
/*
    public void aniadirArea(Area area){
        this.areas.add(area);
    }*/
    public void darseAltaEnOrganizacion(Area area){
        this.solicitudes.add(new Solicitud(this,area));
    }

    public double calcularHC(Integer anio,Integer mes,Organizacion organizacion){
        List<Double> mapped = trayectos.stream().map(e->e.calcularHC(anio,mes,organizacion)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
    public double calcularHCPorcentual(Integer anio,Integer mes,Area area){
        return (this.calcularHC(anio,mes,this.decirOrganizacion(area))/this.decirOrganizacion(area).calcularHC(anio,mes))*100;
    }

    public Organizacion decirOrganizacion(Area area){
        return area.getOrganizacion(); //TODO consultar si es necesario que el miembro diga su organizacion, si lo evitamos mejoramos el encapsulamiento
    }

    public void recibirSolicitud(Tramo tramo){
        this.tramosCompartidosAAceptar.add(tramo);
    }

    public void gestionarTramosCompartidos(Tramo tramo, Trayecto trayecto, boolean respuesta ){
        this.tramosCompartidosAAceptar.remove(tramo);
        if(respuesta) {
            trayecto.agregarIntegranteATramo(tramo);
        }
        //en caso de que sea rechazado no lo va a hacer nada
    }

    public Trayecto generarTrayecto(String descripcion,List<Organizacion> organizaciones,Integer anio, Integer semestre, Integer diasAlMes){
        Trayecto nuevoTrayecto = new Trayecto(descripcion,organizaciones,diasAlMes,anio,semestre,this);
        trayectos.add(nuevoTrayecto);
        return nuevoTrayecto;
    }
}