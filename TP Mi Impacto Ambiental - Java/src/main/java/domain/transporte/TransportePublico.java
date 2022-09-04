package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import javax.persistence.*;
import java.io.IOException;
@Entity
@DiscriminatorValue("TransportePublico")
public class TransportePublico extends Transporte {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    private Linea linea;

    public TransportePublico(SubTipoTransporte subTipoTransporte, Linea linea, CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        super(consumoXKm,calculadorAdapter,subTipoTransporte);
        this.linea = linea;
    }

    public TransportePublico() {

    }

    public TipoTransporte tipoTransporte(){
        return TipoTransporte.TIPO_PUBLICO;
    }
    public TipoActividadDA tipoActividadDA(){
        return TipoActividadDA.TRANSPORTE_PUBLICO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        if(this.subTipoTransporte.getNombre() == "COLECTIVO"){
            return TipoConsumoDA.COLECTIVO_BASE;
        }
        if(this.subTipoTransporte.getNombre() == "SUBTE"){
            return TipoConsumoDA.SUBTE_BASE;
        }
        if(this.subTipoTransporte.getNombre() == "TREN") {
            return TipoConsumoDA.TREN_BASE;
        }
        return null;
    }

}
