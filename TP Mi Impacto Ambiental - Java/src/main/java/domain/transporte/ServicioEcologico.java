package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.IOException;

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
