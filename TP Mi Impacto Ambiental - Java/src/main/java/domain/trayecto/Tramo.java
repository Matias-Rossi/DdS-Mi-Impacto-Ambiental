package domain.trayecto;

import domain.calculadorHC.*;
import domain.calculadorHC.ActividadesEmisorasCO2;
import domain.perfil.Miembro;
import domain.perfil.Organizacion;
import domain.reportes.Periodo;
import domain.reportes.Reportes;
import domain.transporte.TipoTransporte;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;

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
    public Tramo(Ubicacion partida, Ubicacion llegada, Transporte transporte){
        this.partida = partida;
        this.llegada = llegada;
        this.medioDeTransporte = transporte;
        //this.distancia = transporte.calcularDistancia(partida, llegada); ;
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
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "medioDeTransportes_id", referencedColumnName = "id")
    public Transporte medioDeTransporte;
    @Getter
    @Column(name = "cant_integrantes")
    public int integrantes = 0;
    @Column(name = "distancia")
    public double distancia;


    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "factorDeEmision_id", referencedColumnName = "id")
    private FactorDeEmision factorDeEmision;

    public double calcularHC(Integer organizaciones, Integer diasAlMes, Integer mesInicio, Integer mesFin, Integer mes, Organizacion organizacion,Integer anio) {
        if(this.factorDeEmision==null) actualizarFE();
        double hcTotalTramo = CalculadorDeHC.getInstance().calcularHC( this.factorDeEmision,this.valorDA() ) ;
        double hcPorIntegrante = hcTotalTramo / this.integrantes;
        double HCdiario = hcPorIntegrante / organizaciones;
        double HCxMes = HCdiario*diasAlMes;
        if(mes.equals(0)) return this.generarReporte(organizacion, Periodo.Anual,HCxMes*6,anio);
        if(mesInicio<mes && mes<=mesFin*6)return this.generarReporte(organizacion,Periodo.getPeriodo(mes),HCxMes,anio);
        return 0;
    }

    private double generarReporte(Organizacion organizacion,Periodo mes,double hc,Integer anio){
        Reportes reportes = new Reportes(this.factorDeEmision.getTipoActividad(),this.factorDeEmision.getTipoConsumo(),anio,mes,hc,organizacion);
        return hc;
    }

    private void actualizarFE(){
        this.factorDeEmision = CalculadorDeHC.getInstance().devolverFactorDeEmision(this.generarDatoDeActividad());
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
