package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class ServicioEcologico implements Transporte{
    private TipoEcologico tipo;
    private CalculadorDeDistancia calculadorAdapter;
    private double consumoXKm;

    public ServicioEcologico(TipoEcologico tipo, CalculadorDeDistancia calculadorAdapter,double consumoXKm ){
        this.tipo= tipo;
        this.calculadorAdapter = calculadorAdapter;
        this.consumoXKm = consumoXKm;
    }


    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        try {
            return calculadorAdapter.calcularDistancia(inicio,fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double consumoDeTransoporte(){
        return consumoXKm;
    }
    public TipoActividadDA tipoActividadDA(){
        return TipoActividadDA.SERVICIO_ECOLOGICO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        return TipoConsumoDA.SERVICIO_ECOLOGICO_BASE;
    }
    public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_ECOLOGICO;
    }

}
