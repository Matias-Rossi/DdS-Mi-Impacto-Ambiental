package proservices.models.entities.transporte;

import proservices.models.entities.calculadorHC.TipoActividadDA;
import proservices.models.entities.calculadorHC.TipoConsumoDA;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ServicioContratado")
public class ServicioContratado extends Transporte {

    public ServicioContratado(SubTipoTransporte subTipoTransporte,CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        super(consumoXKm,calculadorAdapter,subTipoTransporte);
    }

    public TipoTransporte tipoTransporte(){
        return TipoTransporte.TIPO_CONTRATADO;
    }

    public ServicioContratado() {

    }

    public TipoActividadDA tipoActividadDA(){
        return TipoActividadDA.SERVICIO_CONTRATADO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        return TipoConsumoDA.SERVICIO_CONTRATADO_BASE;
    }
}
