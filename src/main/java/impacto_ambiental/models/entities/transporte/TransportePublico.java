package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import lombok.Getter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TransportePublico")
public class TransportePublico extends Transporte {
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    private Linea linea;

    public TransportePublico(SubTipoTransporte subTipoTransporte, Linea linea, CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        super(consumoXKm,calculadorAdapter,subTipoTransporte);
        this.linea = linea;
        linea.agregarTransporte(this);
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
        if(this.subTipoTransporte.getNombre() == "SUBTE"){
            return TipoConsumoDA.SUBTE_BASE;
        }
        if(this.subTipoTransporte.getNombre() == "TREN") {
            return TipoConsumoDA.TREN_BASE;
        }
        return TipoConsumoDA.COLECTIVO_BASE;
    }

}
