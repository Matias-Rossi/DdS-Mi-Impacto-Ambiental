package domain.transporte;

import domain.ubicacion.Ubicacion;

public class Particular {
    private TipoParticular tipo;
    private TipoCombustible combustible;
    private CalculadorDeDistancia calculadorAdapter;

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        return calculadorAdapter.calcularDistancia(inicio, fin);
    }

    public TipoTransporte decirTipoTransporte() {return TipoTransporte.TIPO_PARTICULAR;}
    private int indiceHC(){
        return 1;
    }
}
