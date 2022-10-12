package proservices.models.entities.transporte;


import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.ubicacion.Ubicacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
@Entity
@Table(name = "lineas")
public class Linea extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "linea",cascade = javax.persistence.CascadeType.ALL,fetch = javax.persistence.FetchType.LAZY)
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
