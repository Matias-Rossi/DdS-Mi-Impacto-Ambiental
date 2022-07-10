package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class TransportePublico implements Transporte {
    private TipoTransportePublico tipo;
    private Linea linea;
    private CalculadorDeDistancia calculadorAdapter;
    private double consumoPercapitaXKm;

    public TransportePublico(TipoTransportePublico tipo, Linea linea, CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        this.tipo = tipo;
        this.linea = linea;
        this.calculadorAdapter = calculadorAdapter;
        this.consumoPercapitaXKm = consumoXKm;
    }



    //@Override
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin) {
        try {
            return calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double consumoDeTransoporte(){
        return consumoPercapitaXKm;
    }
    public TipoActividadDA tipoDeActividadDA(){
        return TipoActividadDA.TRANSPORTE_PUBLICO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        if(this.tipo == TipoTransportePublico.COLECTIVO) {
            return TipoConsumoDA.COLECTIVO_BASE;
        }
        if(this.tipo == TipoTransportePublico.SUBTE) {
            return TipoConsumoDA.SUBTE_BASE;
        }
        if(this.tipo == TipoTransportePublico.TREN) {
            return TipoConsumoDA.TREN_BASE;
        }
        return null;
    }
    public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_PUBLICO;
    }

}
