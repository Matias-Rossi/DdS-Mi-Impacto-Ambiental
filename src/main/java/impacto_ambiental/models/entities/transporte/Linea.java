package impacto_ambiental.models.entities.transporte;


import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "lineas")
public class Linea extends EntidadPersistente {

    @Getter
    @Column(name = "nombre")
    private String nombre;

    @Getter
    @OneToMany(mappedBy = "linea",cascade = javax.persistence.CascadeType.ALL,fetch = javax.persistence.FetchType.LAZY)
    private List<Parada> paradas=new ArrayList<>();

    @Getter
    @OneToOne
    @JoinColumn(name = "transporte_id" , referencedColumnName = "id")
    public TransportePublico transporte;

    public Linea() {

    }


    private Parada obtenerParadaDeUbicacion(List<Parada> paradas, Ubicacion ubicacion) {
        for (Parada parada : paradas) {
            if(parada.getUbicacion().equals(ubicacion)) {
                return parada;
            }
        }
        return null;
    }

    public void agregarParada(Parada par){
        paradas.add(par);
    }

    public void agregarTransporte(TransportePublico trans){
        this.transporte=trans;
    }

    public Linea(String a){
        this.nombre=a;
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
