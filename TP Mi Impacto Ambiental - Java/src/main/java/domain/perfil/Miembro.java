package domain.perfil;

import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;

public class Miembro {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Area> areas;
    private domain.perfil.Miembro Miembro; //TODO ver que onda con el Miembro
    private List<Trayecto> trayectos;
    private list<Tramos> tramosCompartidosAAceptar;

    public void darseAltaEnOrganizacion(Area area){
        area.agregarAMiembroPendiente(this.Miembro);
    }

    public double calcularHC(){
        //TODO calcularHC
        return 1.0;
    }

    public Organizacion decirOrganizacion(Area area){
        return area.getOrganizacion(); //TODO consultar si es necesario que el miembro diga su organizacion, si lo evitamos mejoramos el encapsulamiento
    }

    public void recibirSolicitud(Tramo tramo){
        this.tramosCompartidosAAceptar.add(tramo);
    }

    public void gestionarTramosCompartidos(Tramo tramoCompartido, Trayecto miTrayecto, boolean respuesta ){
        this.tramosCompartidosAAceptar.remove(tramoCompartido);
        if(respuesta) {
            miTrayecto.agregarTramo(tramoCompartido);
        }
        //en caso de que sea rechazado no lo va a hacer nada
    }

    private Trayecto generarTrayecto(String descripcion){
        trayectos.add(new Trayecto(descripcion));
    }
}