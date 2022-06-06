package domain.transporte;

import domain.ubicacion.Ubicacion;

public class TransportePublico implements Transporte {
    private TransportePublico tipo;
    private Linea linea;
    private CalculadorDeDistancia calculadorAdapter;

    public int indiceHC(){

        return 1;
    }

    @Override
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin) {

        return calculadorAdapter.calcularDistancia(inicio, fin);
    }

}
