package impacto_ambiental.models.entities.trayecto;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.transporte.Transporte;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Entity
@Table(name = "trayectos")
public class Trayecto extends EntidadPersistente {


    @Column(name = "hc")
    private double hc;
    @ManyToOne() @Setter
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    @Column(name = "anio") @Setter
    private Integer anio;
    @Column(name = "semestre") @Setter
    private Integer semestre;
    @Column(name = "diasAlMes") @Setter
    private  Integer diasAlMes;
    @Column(name = "descripcion") @Setter @Getter
    public String descripcion;
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
//    @Setter
//    @JoinTable(name = "trayectos_organizaciones",
//            joinColumns = @JoinColumn(name = "trayecto_id"),
//            inverseJoinColumns = @JoinColumn(name = "organizacion_id"))
//    private List<Organizacion> organizaciones = new ArrayList<>();

    @Getter
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY,mappedBy = "trayecto")
    List<TrayectosPorOrganizaciones> organizacionesxtrayectos = new ArrayList<>();
    @Setter
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "trayectos_tramos",
            joinColumns = @JoinColumn(name = "trayecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tramo_id"))
    private List<Tramo> tramos = new ArrayList<Tramo>();

    public Trayecto() {

    }

    private Ubicacion fin(){
        return (this.tramos.get(this.tramos.size()-1)).getLlegada();
    }
    private Ubicacion inicio(){
        return (this.tramos.get(0)).getPartida();
    }


    public void calcularHC(Organizacion organizacion)
    {
        List<TrayectosPorOrganizaciones> organizacionesxtrayectos = this.organizacionesxtrayectos.stream().filter(t -> t.getOrganizacion().equals(organizacion)).collect(Collectors.toList());
        if(organizacionesxtrayectos.size() == 0){
            return;
        }
        TrayectosPorOrganizaciones trayectosPorOrganizaciones = organizacionesxtrayectos.get(0);
        if(Objects.isNull(trayectosPorOrganizaciones.getHc())) {
            double hcxOrg = tramos.stream().map(e->e.calcularHC(this.organizacionesxtrayectos.size(),this.semestre,this.anio,trayectosPorOrganizaciones.getOrganizacion(),this.miembro)).collect(Collectors.toList()).stream().reduce(0.0, (a, b) ->a+b);
            trayectosPorOrganizaciones.setHc(hcxOrg);
        }
    }

    public void calcularHC()
    {
        this.organizacionesxtrayectos.stream().forEach(e->this.calcularHC(e.getOrganizacion()));
    }

    public Trayecto(String descripcion, List<Organizacion> organizaciones, Integer diasAlMes,Integer anio,Integer semestre,Miembro miembro){
        this.miembro = miembro;
        this.diasAlMes = diasAlMes;
        this.anio = anio;
        this.semestre = semestre;
        this.descripcion = descripcion;
        this.organizacionesxtrayectos = organizaciones.stream().map(e->new TrayectosPorOrganizaciones(e,this)).collect(Collectors.toList());
        this.diasAlMes = diasAlMes;
    }

    public void setOrganizaciones(List<Organizacion> orgs){
        this.organizacionesxtrayectos = orgs.stream().map(e->new TrayectosPorOrganizaciones(e,this)).collect(Collectors.toList());
    }

    public Tramo aniadirNuevoTramo(Ubicacion salida, Ubicacion llegada, Transporte transporte){
        Tramo nuevoTramo = new Tramo(salida, llegada, transporte, diasAlMes);
        this.agregarIntegranteATramo(nuevoTramo);
        return  nuevoTramo;
    }

    public void agregarIntegranteATramo(Tramo tramo){
        this.tramos.add(tramo);
        tramo.sumarIntegrante();
    }

    public double calcularDistanciaTotal() {
        double distanciaTotal = 0;
        for (Tramo tramo : this.tramos) {
            distanciaTotal += tramo.getDistancia();
        }
        return distanciaTotal;
    }
    public void eliminarTramo(Tramo tramo){
        tramos.remove(tramo);
    }
}
