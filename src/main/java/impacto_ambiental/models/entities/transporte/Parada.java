package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Table(name = "paradas")
public class Parada extends EntidadPersistente {
    @Getter
    @Column
    private String nombre;

    @Getter
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
        this.linea.agregarParada(this);
    }

    public Parada(String nombre, Ubicacion ubicacion, int index, float distanciaASiguiente, float distanciaAAnterior, Linea linea) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.index = index;
        this.distanciaASiguiente = distanciaASiguiente;
        this.linea = linea;
        this.linea.agregarParada(this);
    }

    public Parada() {

    }
}
