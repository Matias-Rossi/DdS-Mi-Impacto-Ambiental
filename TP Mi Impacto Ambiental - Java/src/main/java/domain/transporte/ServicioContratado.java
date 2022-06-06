package domain.transporte;

import domain.ubicacion.Ubicacion;

public class ServicioContratado {
    private TipoEcologico tipo;
    private CalculadorDeDistancia calculadorAdapter;

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        return calculadorAdapter.calcularDistancia(inicio,fin);
    }

    public TipoTransporte decirTipoTransporte() {return TipoTransporte.TIPO_CONTRATADO;}

    private int indiceHC(){
        return 1;
    }
}
