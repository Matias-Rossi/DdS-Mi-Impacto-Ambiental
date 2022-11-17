package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;

import javax.persistence.*;

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
        System.out.println("LO BUSCO CON "+this.subTipoTransporte.getNombre()+this.combustible);
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
        if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.AUTO_ELECTRICO;
        if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.AUTO_GASOIL;
        if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.AUTO_GNC;
       return TipoConsumoDA.AUTO_NAFTA;
    }
    /*public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_PARTICULAR;
    }*/
}