package domain.trayecto;

import domain.calculadorHC.*;
import domain.calculadorHC.ActividadesEmisorasCO2;
import domain.perfil.Miembro;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "tramos")
public class Tramo implements ActividadesEmisorasCO2{
    @Id
    @GeneratedValue
    private int id;

    public Tramo() {

    }

    public int getId() {
        return id;
    }
    public Tramo(Ubicacion partida, Ubicacion llegada, Transporte transporte,CalculadorDeHC calculadorDeHC){
        this.calculadorDeHC = calculadorDeHC;
        this.partida = partida;
        this.llegada = llegada;
        this.medioDeTransporte = transporte;
        this.distancia = transporte.calcularDistancia(partida, llegada); ;
    }
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_partida_id", referencedColumnName = "id")
    private Ubicacion partida;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_llegada_id", referencedColumnName = "id")
    private Ubicacion llegada;
    @Getter
    /*
    @ManyToOne
    @JoinColumn(name = "medioDeTransportes_id", referencedColumnName = "id")

     */
    @ManyToOne
    @JoinColumn(name = "medioDeTransportes_id", referencedColumnName = "id")
    public Transporte medioDeTransporte;
    @Transient
    private CalculadorDeHC calculadorDeHC;
    @Getter
    @Column(name = "cant_integrantes")
    public int integrantes = 0;
    @Column(name = "distancia")
    public double distancia;

    public double calcularHC(){
        return calculadorDeHC.calcularHC( this.generarDatoDeActividad() )/ this.integrantes ;
    }

    public void compartirTramo(Miembro miembro){
        if ((this.medioDeTransporte.tipoTransporte() == TipoTransporte.TIPO_CONTRATADO) || (this.medioDeTransporte.tipoTransporte() == TipoTransporte.TIPO_PARTICULAR)){
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
        return this.distancia * this.medioDeTransporte.getConsumoXKm();
    }
    public DatoDeActividad generarDatoDeActividad() {
        return new DatoDeActividad(this.medioDeTransporte.tipoActividadDA(), this.medioDeTransporte.tipoConsumoDA(), this.valorDA());
    }
}
