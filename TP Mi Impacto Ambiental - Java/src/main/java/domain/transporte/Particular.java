package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.ubicacion.Ubicacion;

import java.io.IOException;

public class Particular implements Transporte {

    private TipoParticular tipo;
    private TipoCombustible combustible;
    private CalculadorDeDistancia calculadorAdapter;
    private double consumoXKm;

    public Particular(TipoParticular tipo, TipoCombustible combustible, CalculadorDeDistancia calculadorAdapter,double consumoXKm) {
        this.tipo = tipo;
        this.combustible = combustible;
        this.calculadorAdapter = calculadorAdapter;
        this.consumoXKm = consumoXKm;
    }

    public double calcularDistancia(Ubicacion inicio, Ubicacion fin){
        try {
            return calculadorAdapter.calcularDistancia(inicio, fin);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public double consumoDeTransoporte(){
        return this.consumoXKm;
    }
    public TipoActividadDA tipoDeActividadDA(){
        return TipoActividadDA.TRANSPORTE_PUBLICO;
    }
    public TipoConsumoDA tipoConsumoDA(){
        if(this.tipo == TipoParticular.AUTO){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.AUTO_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.AUTO_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.AUTO_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.AUTO_NAFTA;
        }
        if(this.tipo == TipoParticular.CAMIONETA){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.CAMIONETA_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.CAMIONETA_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.CAMIONETA_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.CAMIONETA_NAFTA;
        }
        if(this.tipo == TipoParticular.MOTO){
            if(this.combustible == TipoCombustible.ELECTRICO) return TipoConsumoDA.MOTO_ELECTRICO;
            if(this.combustible == TipoCombustible.GASOIL) return TipoConsumoDA.MOTO_GASOIL;
            if(this.combustible == TipoCombustible.GNC) return TipoConsumoDA.MOTO_GNC;
            if(this.combustible == TipoCombustible.NAFTA) return TipoConsumoDA.MOTO_NAFTA;
        }
        return null;
    }
    public TipoTransporte decirTipoTransporte(){
        return TipoTransporte.TIPO_PARTICULAR;
    }
}