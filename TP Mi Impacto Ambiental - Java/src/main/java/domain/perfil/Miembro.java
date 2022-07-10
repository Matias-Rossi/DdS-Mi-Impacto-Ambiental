package domain.perfil;

import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Miembro {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Area> areas = new ArrayList<Area>();
    private List<Trayecto> trayectos = new ArrayList<Trayecto>();
    @Getter
    private List<Tramo> tramosCompartidosAAceptar = new ArrayList<Tramo>();

    Miembro(String nombre, String apellido, TipoDocumento tipoDocumento, Integer numeroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public void darseAltaEnOrganizacion(Area area){
        area.agregarAMiembroPendiente(this);
    }

    public double calcularHC(Integer anio,Integer mes){
        List<Double> mapped = trayectos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
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

    public Trayecto generarTrayecto(String descripcion,List<Organizacion> organizaciones,Integer anio, Integer semestre, Integer diasAlMes){
        Trayecto nuevoTrayecto = new Trayecto(descripcion,organizaciones,diasAlMes,anio,semestre);
        trayectos.add(nuevoTrayecto);
        return nuevoTrayecto;
    }
}