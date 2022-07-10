package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class ServicioContratado implements Transporte  {
    private TipoTransporteContratado tipo;
    private CalculadorDeDistancia calculadorAdapter;
    private double consumoXKm;

    public ServicioContratado(TipoTransporteContratado tipo,CalculadorDeDistancia calculadorAdapter,double consumoXKm){
        this.tipo = tipo;
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
        return this.consumoXKm;
    }
    public TipoActividadDA tipoDeActividadDA(){
        return TipoActividadDA.SERVICIO_CONTRATADO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        return TipoConsumoDA.SERVICIO_CONTRATADO_BASE;
    }
    public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_CONTRATADO;
    }
}
