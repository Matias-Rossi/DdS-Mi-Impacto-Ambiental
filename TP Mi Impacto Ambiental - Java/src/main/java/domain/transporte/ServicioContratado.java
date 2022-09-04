package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.IOException;

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
