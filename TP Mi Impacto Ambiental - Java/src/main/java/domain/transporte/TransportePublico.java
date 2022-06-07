package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class TransportePublico implements Transporte {
    private TransportePublico tipo;
    private Linea linea;
    private CalculadorDeDistancia calculadorAdapter;

    public TransportePublico(TransportePublico tipo, Linea linea, CalculadorDeDistancia calculadorAdapter){
        this.tipo = tipo;
        this.linea = linea;
        this.calculadorAdapter = calculadorAdapter;
    }

    public int indiceHC(){

        return 1;
    }

    public TipoTransporte decirTipoTransporte() {return TipoTransporte.TIPO_PUBLICO;}
    @Override
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin) {
        try {
            return calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
