package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.servicios.geodds.ServicioGeoDds;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;
import java.io.IOException;
@Entity
@Table(name = "transportes")
@Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_transporte",discriminatorType = DiscriminatorType.STRING)
public abstract class Transporte extends EntidadPersistente {

    @Transient
    protected CalculadorDeDistancia calculadorAdapter;

    {
        try {
            calculadorAdapter = ServicioGeoDds.getInstancia();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Getter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "subtipo_id", referencedColumnName = "id")
    protected SubTipoTransporte subTipoTransporte;

    @Getter
    @Column(name = "consumo_x_km")
    protected double consumoXKm;

    public TipoTransporte tipoTransporte(){
        return null;
    }

    public TipoConsumoDA tipoConsumoDA(){
        return null;
    }
    public TipoActividadDA tipoActividadDA(){
        return null;
    }

    public Transporte() {

    }

    public Transporte(double consumoXKm, CalculadorDeDistancia calculadorAdapter, SubTipoTransporte subTipoTransporte){
        this.subTipoTransporte = subTipoTransporte;
        this.calculadorAdapter = calculadorAdapter;
        this.consumoXKm = consumoXKm;
    }
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        //return inicio.getNumeracion()*7;

        try {
            return this.calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;


    }
}
