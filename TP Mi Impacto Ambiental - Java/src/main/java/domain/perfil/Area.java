package domain.perfil;

import java.util.List;

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

    public void agregarMiembro(Miembro nuevoMiembro){
        this.miembros.add(nuevoMiembro);
    }


    public void agregarAMiembroPendiente(Miembro nuevoMiembro){
        this.miembrosPendientes.add(nuevoMiembro);
    }

    public int calcularHC(){
        //TODO calcularHC
        return 1;
    }

}
