package domain.transporte;


import domain.ubicacion.Ubicacion;

import java.util.List;

public class Linea {
    private String nombre;
    private List<Parada> paradas;

    private Parada obtenerParadaDeUbicacion(List<Parada> paradas, Ubicacion ubicacion) {
        for (Parada parada : paradas) {
            if(parada.getUbicacion().equals(ubicacion)) {
                return parada;
            }
        }
        return null;
    }

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin) {
        Parada paradaInicio = obtenerParadaDeUbicacion(paradas, inicio);
        Parada paradaFin = obtenerParadaDeUbicacion(paradas, fin);
        if(paradaFin.getIndex() > paradaInicio.getIndex()){
            return distanciaEntreParadas(paradaInicio,paradaFin);
        }
        return distanciaEntreParadas(paradaFin,paradaInicio);

    }
    public double distanciaEntreParadas(Parada paradaInicio, Parada paradaFin){  //TODO revisar funcionam
        return paradas.stream()
            .filter(unaParada -> unaParada.getIndex() < paradaFin.getIndex() ||  unaParada.getIndex() <= paradaInicio.getIndex())
            .map(unaParada -> unaParada.getDistanciaASiguiente()).reduce((float) 0, (a, b) -> a + b);

    }
}
