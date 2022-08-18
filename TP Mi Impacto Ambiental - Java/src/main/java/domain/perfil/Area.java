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
    public String nombre;
    @ManyToOne
    @JoinColumn(name = "organizaciones_id", referencedColumnName = "id")
    private Organizacion organizacion;
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<Miembro> miembros = new ArrayList<>();
    @Transient
    private List<Miembro> miembrosPendientes = new ArrayList<>();

    public Area(String nombreArea, Organizacion nombreOrganizacion) {
        this.nombre = nombreArea;
        this.organizacion = nombreOrganizacion;
    }

    public Area() {

    }

    public Organizacion getOrganizacion() {
        return this.organizacion;
    }

    public void gestionarMiembrosPendientes(Integer indice, boolean respuesta){
        Miembro miembroAAniadir = this.miembrosPendientes.get(indice);
        this.miembrosPendientes.remove(miembroAAniadir);
        if(respuesta){
            miembroAAniadir.aniadirArea(this);
            this.miembros.add(miembroAAniadir);
        }
    }

    public void agregarAMiembroPendiente(Miembro nuevoMiembro){
        this.miembrosPendientes.add(nuevoMiembro);
    }

    public double calcularHCporMiembro(Integer anio,Integer mes){
        return this.calcularHC(anio,mes)/this.miembros.size();
    }
    public double calcularHC(Integer anio, Integer mes){

        List<Double> mapped = miembros.stream().map(e->e.calcularHC(anio,mes,this.organizacion)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }

}
