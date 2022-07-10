package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class Particular  {

    private TipoParticular tipo;
    private TipoCombustible combustible;
    private CalculadorDeDistancia calculadorAdapter;

    public Particular(TipoParticular tipo, TipoCombustible combustible, CalculadorDeDistancia calculadorAdapter) {
        this.tipo = tipo;
        this.combustible = combustible;
        this.calculadorAdapter = calculadorAdapter;
    }

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        try {
            return calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public TipoTransporte decirTipoTransporte() {return TipoTransporte.TIPO_PARTICULAR;}


}
