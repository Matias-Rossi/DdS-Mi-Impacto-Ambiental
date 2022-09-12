package domain.transporte;

import domain.persistenceExtend.EntidadPersistente;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "paradas")
public class Parada extends EntidadPersistente {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    private Linea linea;
    @Getter
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", referencedColumnName = "id")
    private Ubicacion ubicacion;
    @Getter
    @Column(name = "indice")
    private int index;
    @Getter
    @Column(name = "distancia_a_siguiente")
    private float distanciaASiguiente;
    @Column(name = "distancia_a_anterior")
    private float distanciaAAnterior;   //por el momento no la usamos

    public Parada(Ubicacion ubicacion, int index, float distanciaASiguiente, float distanciaAAnterior, Linea linea) {
        this.ubicacion = ubicacion;
        this.index = index;
        this.distanciaASiguiente = distanciaASiguiente;
        this.linea = linea;
    }

    public Parada() {

    }
}
