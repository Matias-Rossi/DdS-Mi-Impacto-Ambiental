package domain.transporte;
public class TransportePublico implements Transporte {
    private TransportePublico tipo;
    private Linea linea;

    public int indiceHC(){

        return 1;
    }
    public int calcularDistancia(Ubicacion inicio,Ubicacion fin){
        linea.calcularDistancia(inicio,fin);
    }
}
