package domain.trayecto;

import domain.calculadorHC.CalculadorDeHC;
import domain.perfil.Miembro;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntidadPersistente;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "trayectos")
public class Trayecto extends EntidadPersistente {
    @ManyToOne()
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "semestre")
    private Integer semestre;
    @Column(name = "diasAlMes")
    private  Integer diasAlMes;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "trayectos_organizaciones",
            joinColumns = @JoinColumn(name = "trayecto_id"),
            inverseJoinColumns = @JoinColumn(name = "organizacion_id"))
    private List<Organizacion> organizaciones = new ArrayList<>();
    @Getter
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "trayectos_tramos",
            joinColumns = @JoinColumn(name = "trayecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tramo_id"))
    private List<Tramo> tramos = new ArrayList<Tramo>();

    public Trayecto() {

    }

    /*
        private Ubicacion fin(){
            return (this.tramos.get(this.tramos.size()-1)).destino();
        }

        private Ubicacion inicio(){
            return (this.tramos.get(0)).origen();
        }

     */
    public double calcularHC(Integer anio, Integer mes, Organizacion organizacion)
    {
        if((!this.organizaciones.contains(organizacion)) || !anio.equals(this.anio))return 0;

        List<Double> mapped = tramos.stream().map(e->e.calcularHC(this.organizaciones.size(),this.diasAlMes,(this.semestre-1)*6,this.semestre*6,mes,organizacion,anio)).collect(Collectors.toList());
        double HC = (mapped.stream().reduce(0.0, (a, b) ->a+b))/*organizaciones.size()*/;
        return HC;
        /*
        double HCxMes = HCdiario*diasAlMes;
        Integer inicioSem = (this.semestre-1)*6;
        Integer finSem = this.semestre*6;
        if(mes.equals(0)) return HCxMes*6;
        if(inicioSem<mes && mes<=finSem*6)return HCxMes;
        return 0;*/
    }

    public Trayecto(String descripcion, List<Organizacion> organizaciones, Integer diasAlMes,Integer anio,Integer semestre,Miembro miembro){
        this.miembro = miembro;
        this.diasAlMes = diasAlMes;
        this.anio = anio;
        this.semestre = semestre;
        this.descripcion = descripcion;
        this.organizaciones = organizaciones;
        this.diasAlMes = diasAlMes;
    }

    public Tramo aniadirNuevoTramo(Ubicacion salida, Ubicacion llegada, Transporte transporte){
        Tramo nuevoTramo = new Tramo(salida, llegada, transporte);
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
}
