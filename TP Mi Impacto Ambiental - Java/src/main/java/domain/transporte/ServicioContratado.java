package domain.transporte;
public class ServicioContratado {
    private TipoEcologico tipo;
    private CalculadorDeDistancia calculadorAdapter;

    public float calcularDistancia(Ubicacion inicio, Ubicacion fin){
        calculadorAdapter.calcularDistancia(incio,fin);
    }

    private int indiceHC(){
        return 1;
    }
}
