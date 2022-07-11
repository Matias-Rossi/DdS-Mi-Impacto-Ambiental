package domain.perfil;

import java.util.List;
import java.util.stream.Collectors;

public class Area {
    private String nombre;
    private Organizacion organizacion;
    private List<Miembro> miembros ;
    private List<Miembro> miembrosPendientes;

    public Area(String nombreArea, Organizacion nombreOrganizacion) {
        this.nombre = nombreArea;
        this.organizacion = nombreOrganizacion;
    }

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
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
