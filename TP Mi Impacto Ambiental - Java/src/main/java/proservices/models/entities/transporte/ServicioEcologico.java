package proservices.models.entities.transporte;

import proservices.models.entities.calculadorHC.TipoActividadDA;
import proservices.models.entities.calculadorHC.TipoConsumoDA;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ServicioEcologico")
public class ServicioEcologico extends Transporte {
    public ServicioEcologico(SubTipoTransporte subTipoTransporte,CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        super(consumoXKm,calculadorAdapter,subTipoTransporte);
    }

    public TipoTransporte tipoTransporte(){
        return TipoTransporte.TIPO_ECOLOGICO;
    }

    public ServicioEcologico() {

    }

    public TipoActividadDA tipoActividadDA(){
        return TipoActividadDA.SERVICIO_ECOLOGICO;
    }

    public TipoConsumoDA tipoConsumoDA(){
        return TipoConsumoDA.SERVICIO_ECOLOGICO_BASE;
    }

}
