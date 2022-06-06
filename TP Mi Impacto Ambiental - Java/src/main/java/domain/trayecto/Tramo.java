package domain.trayecto;

import domain.perfil.Miembro;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;

public class Tramo {
    public Tramo(Ubicacion partida,Ubicacion llegada, Transporte transporte){
        this.partida = partida;
        this.llegada = llegada;
        this.medioDeTransporte = transporte;
    }
    private Ubicacion partida;
    private Ubicacion llegada;
    private Transporte medioDeTransporte;
    private HCadapter HCAdapterAdapter;
    private int integrantes = 1;

    private double calcularHC(){
        return this.HCAdapterAdapter.calcularHC();
    }
    private void compartirTramo(Miembro miembro){
        if ((medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_CONTRATADO) || (medioDeTransporte.decirTipoTransporte() == TipoTransporte.TIPO_PARTICULAR)){
            miembro.recibirSolicitud(this);
        }else System.err.println("ESTE TRANSPORTE NO PUEDE SER COMPARTIDO");
    }
    public void sumarIntegrante(){
        this.integrantes++;
    }

}
