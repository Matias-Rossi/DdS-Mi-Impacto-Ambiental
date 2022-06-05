package domain.trayecto;

import domain.perfil.Miembro;
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
        miembro.recibirSolicitud(this);
    }
    public void sumarIntegrante(){
        this.integrantes++;
    }

}
