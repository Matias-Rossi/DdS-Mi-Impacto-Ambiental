package domain.trayecto;

import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import org.apache.commons.collections4.iterators.ArrayListIterator;

import java.util.List;

public class Trayecto {
    private String descripcion;
    private List<Tramo> tramos;

    public double calcularHC(){
        return 1.0;
    }

    public Trayecto(String descripcion){
        this.descripcion = descripcion;
    }

    public void aniadirNuevoTramo(Ubicacion salida, Ubicacion llegada, Transporte transporte){
        this.agregarTramo(new Tramo(salida,llegada,transporte));
    }

    public void agregarTramo(Tramo tramo){
        this.tramos.add(tramo);
        tramo.sumarIntegrante();
    }
}
