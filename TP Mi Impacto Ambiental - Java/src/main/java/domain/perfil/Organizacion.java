package domain.perfil;

import domain.ubicacion.Ubicacion;

public class Organizacion{
    private String razonSocial;
    private Tipo tipo;
    private List<Area> areas;
    private Clasificacion clasificacion;
    private Ubicacion ubicacion;
    private List<Miembro> miembrosPendientes;

    public void darAltaArea(Area nuevaArea){
        this.areas.add(nuevaArea);
    }

    public void aceptarEmpleado(Area area, Miembro nuevoMiembro){
        area.agregarMiembro(nuevoMiembro);
        //TODO como sacar de miembros pendientes al nuevo miembro
    }

    public void solicituAceptacion(Miembro miembro){
        this.miembrosPendientes.add(miembro);
    }

    public double calcularHC(){
        return 1.0;

    }
}

