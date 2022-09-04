package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import javax.persistence.*;
import java.io.IOException;

@Entity
@DiscriminatorValue("Particular")
public class Particular extends Transporte {

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_combustible")
    private TipoCombustible combustible;

    public TipoTransporte tipoTransporte(){
        return TipoTransporte.TIPO_PARTICULAR;
    }

    public Particular(SubTipoTransporte subTipoTransporte, TipoCombustible combustible, CalculadorDeDistancia calculadorAdapter,double consumoXKm) {
        super(consumoXKm,calculadorAdapter,subTipoTransporte);
        this.combustible = combustible;
    }

    public Particular() {

    }

    public TipoActividadDA tipoActividadDA(){
        return TipoActividadDA.TRANSPORTE_PARTICULAR;
    }


    public TipoConsumoDA tipoConsumoDA(){
        if(this.subTipoTransporte.getNombre() == "AUTO"){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.AUTO_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.AUTO_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.AUTO_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.AUTO_NAFTA;
        }
        if(this.subTipoTransporte.getNombre() == "CAMIONETA"){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.CAMIONETA_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.CAMIONETA_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.CAMIONETA_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.CAMIONETA_NAFTA;
        }
        if(this.subTipoTransporte.getNombre() == "MOTO"){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.MOTO_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.MOTO_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.MOTO_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.MOTO_NAFTA;
        }
        return null;
    }
    /*public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_PARTICULAR;
    }*/
}