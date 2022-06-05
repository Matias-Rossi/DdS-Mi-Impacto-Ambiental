package domain.transporte;
public class Particular {
    private TipoParticular tipo;
    private TipoCombustible combustible;
    private CalculadorDeDistancia calculadorAdapter;

    public float calcularDistancia(Ubicacion inicio, Ubicacion fin){
        calculadorAdapter.calcularDistancia(incio,fin);
    }

    private int indiceHC(){
        return 1;
    }
}
