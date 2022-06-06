package domain.transporte;

import domain.ubicacion.Ubicacion;
import lombok.Getter;

public class Parada {
    @Getter
    private Ubicacion ubicacion;
    @Getter
    private int index;
    @Getter
    private float distanciaASiguiente;
    private float distanciaAAnterior;   //por el momento no la usamos



}
