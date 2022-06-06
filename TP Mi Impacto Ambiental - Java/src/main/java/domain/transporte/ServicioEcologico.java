package domain.transporte;

import domain.ubicacion.Ubicacion;

public class ServicioEcologico {
    private TipoEcologico tipo;

    private CalculadorDeDistancia calculadorAdapter;

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        return calculadorAdapter.calcularDistancia(inicio,fin);
    }

    private int indiceHC(){
        return 1;
    }

}
