package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class ServicioEcologico {
    private TipoEcologico tipo;
    private CalculadorDeDistancia calculadorAdapter;

    public ServicioEcologico(TipoEcologico tipo, CalculadorDeDistancia calculadorAdapter){
        this.tipo= tipo;
        this.calculadorAdapter = calculadorAdapter;
    }



    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        try {
            return calculadorAdapter.calcularDistancia(inicio,fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public TipoTransporte decirTipoTransporte() {return TipoTransporte.TIPO_ECOLOGICO;}

    private int indiceHC(){
        return 1;
    }

}
