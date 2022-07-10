package domain.trayecto;

import domain.calculadorHC.*;
import domain.calculadorHC.ActividadesEmisorasCO2;
import domain.perfil.Miembro;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

public class Tramo implements ActividadesEmisorasCO2 {
    public Tramo(Ubicacion partida, Ubicacion llegada, Transporte transporte,CalculadorDeHC calculadorDeHC){
        this.calculadorDeHC = calculadorDeHC;
        this.partida = partida;
        this.llegada = llegada;
        this.medioDeTransporte = transporte;
    }
    private Ubicacion partida;
    private Ubicacion llegada;
    private Transporte medioDeTransporte;
    private CalculadorDeHC calculadorDeHC;
    @Getter
    private int integrantes = 1;
    double valorDA = this.getDistancia() * medioDeTransporte.consumoDeTransoporte() ;



    public double calcularHC(){
        return calculadorDeHC.calcularHC( generarDatoDeActividad(medioDeTransporte.tipoDeActividadDA() , medioDeTransporte.tipoConsumoDA(),this.valorDA ) )/this.integrantes ;

    }
    public void compartirTramo(Miembro miembro){
        if ((medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_CONTRATADO) || (medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_PARTICULAR)){
            miembro.recibirSolicitud(this);
        }else System.err.println("ESTE TRANSPORTE NO PUEDE SER COMPARTIDO");
    }
    public void sumarIntegrante(){
        this.integrantes++;
    }

    public double getDistancia() {
        return medioDeTransporte.calcularDistancia(partida, llegada);
    }

    public DatoDeActividad generarDatoDeActividad(TipoActividadDA tipoActividadDA , TipoConsumoDA tipoConsumo, double valorDA) {
        return new DatoDeActividad(tipoActividadDA, tipoConsumo, valorDA);
    }
}
