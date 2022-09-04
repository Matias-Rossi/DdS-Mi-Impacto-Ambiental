package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.persistenceExtend.EntidadPersistente;
import domain.ubicacion.Ubicacion;
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
    @ManyToOne
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
        try {
            return this.calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
