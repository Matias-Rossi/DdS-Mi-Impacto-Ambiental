package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;
import impacto_ambiental.models.entities.usuario.Usuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "miembros")
public class Miembro extends EntidadPersistente {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "miembro")
    List<HChistorico> hcsHistoricos = new ArrayList<>();

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private Ubicacion ubicacion;

    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_doc")
    private TipoDocumento tipoDocumento;
    @Column(name = "numero_doc")
    private String numeroDocumento;
    /*
    @ManyToMany
    private List<Area> areas = new ArrayList<Area>();
*/
    @Getter
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "miembro")
    public List<Solicitud> solicitudes = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "miembro")
    private List<Trayecto> trayectos = new ArrayList<Trayecto>();
    @Getter
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(
            name = "tramos_a_aceptar_por_miembro",
            joinColumns = @JoinColumn(name = "miembros_id"),
            inverseJoinColumns = @JoinColumn(name = "tramos_id")
    )
    private List<Tramo> tramosCompartidosAAceptar = new ArrayList<Tramo>();

    public Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento,Ubicacion ubicacion,String mail,String usuario,Usuario usuario1) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.ubicacion=ubicacion;
        this.usuario=usuario1;
    }

    public Miembro(Usuario usuario) {
        this.usuario = usuario;
    }

    public Miembro() {

    }
/*
    public void aniadirArea(Area area){
        this.areas.add(area);
    }*/
    public Solicitud darseAltaEnOrganizacion(Area area){
        Solicitud solicitud = new Solicitud(this,area);
        this.solicitudes.add(solicitud);
        return solicitud;
    }

    public void calcularHC(Organizacion organizacion){
        trayectos.stream().forEach(e->e.calcularHC(organizacion));
    }
    public double calcularHCPorcentual(Integer anio,Integer mes,Area area){
//        return (this.calcularHC(this.decirOrganizacion(area))/this.decirOrganizacion(area).calcularHC(anio,mes))*100;
        //TODO
        return 0.0;
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

    public void agregarHC(HChistorico hc){
        hcsHistoricos.add(hc);
    }
}