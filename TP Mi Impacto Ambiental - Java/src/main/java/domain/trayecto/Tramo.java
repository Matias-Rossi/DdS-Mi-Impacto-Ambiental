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
        this.distancia = transporte.calcularDistancia(partida, llegada); ;
    }
    private Ubicacion partida;
    private Ubicacion llegada;
    @Getter
    public Transporte medioDeTransporte;
    private CalculadorDeHC calculadorDeHC;
    @Getter
    public int integrantes = 0;
    public double distancia;

    public double calcularHC(){
        return calculadorDeHC.calcularHC( this.generarDatoDeActividad() )/ this.integrantes ;
    }

    public void compartirTramo(Miembro miembro){
        if ((this.medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_CONTRATADO) || (this.medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_PARTICULAR)){
            miembro.recibirSolicitud(this);
        }else System.err.println("ESTE TRANSPORTE NO PUEDE SER COMPARTIDO");
    }

    public void sumarIntegrante(){
        this.integrantes++;
    }
    public double getDistancia() {
        return this.distancia;
    }
    public double valorDA(){
        return this.distancia * this.medioDeTransporte.consumoDeTransoporte();
    }
    public DatoDeActividad generarDatoDeActividad() {
        return new DatoDeActividad(this.medioDeTransporte.tipoActividadDA(), this.medioDeTransporte.tipoConsumoDA(), this.valorDA());
    }
}
