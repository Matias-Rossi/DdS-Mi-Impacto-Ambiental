package impacto_ambiental.models.entities.trayecto;

import impacto_ambiental.models.entities.calculadorHC.ActividadesEmisorasCO2;
import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.Periodo;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.transporte.TipoTransporte;
import impacto_ambiental.models.entities.transporte.Transporte;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;
import lombok.Setter;
import impacto_ambiental.models.entities.calculadorHC.CalculadorDeHC;
import impacto_ambiental.models.entities.calculadorHC.DatoDeActividad;
import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;

@Entity
//@Table(name = "tramos")
public class Tramo implements ActividadesEmisorasCO2 {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "hc")
    private double hcXIntegrantes;

    @Column(name = "diasAlMes")
    private Integer diasAlMes;

    public Tramo() {

    }

    public int getId() {
        return id;
    }
    public Tramo(Ubicacion partida, Ubicacion llegada, Transporte transporte, Integer diasAlMes){
        this.partida = partida;
        this.llegada = llegada;
        this.medioDeTransporte = transporte;
        //this.distancia = transporte.calcularDistancia(partida, llegada); ;
        this.diasAlMes = diasAlMes;
    }

    @Getter @Setter
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_partida_id", referencedColumnName = "id")
    private Ubicacion partida;

    @Getter @Setter
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ubicacion_llegada_id", referencedColumnName = "id")
    private Ubicacion llegada;
    @Getter
    /*
    @ManyToOne
    @JoinColumn(name = "medioDeTransportes_id", referencedColumnName = "id")

     */
    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "medioDeTransportes_id", referencedColumnName = "id")
    public Transporte medioDeTransporte;
    @Getter @Setter
    @Column(name = "cant_integrantes")
    public int integrantes = 0;
    @Column(name = "distancia") @Setter
    public double distancia;


    @Setter
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "factorDeEmision_id", referencedColumnName = "id")
    private FactorDeEmision factorDeEmision;

    public double calcularHC(Integer size, Integer mesInicio, Integer mesFin, Integer anio, Organizacion org,Miembro miembro) {
        if(Objects.isNull(this.hcXIntegrantes)) this.cargarHc();


        double hcXorgXpart = this.hcXIntegrantes/size;


        for(;mesInicio<=mesFin;mesInicio++){
            Integer month = mesInicio;
            new HChistorico(this.factorDeEmision.getTipoActividad(), this.factorDeEmision.getTipoConsumo(), anio, Periodo.getPeriodo(month), hcXorgXpart,org,miembro);
        }
/*
        if(mes.equals(0)) return this.generarReporte(organizacion, Periodo.Anual,HCxMes*6,anio);
        if(mesInicio<mes && mes<=mesFin*6)return this.generarReporte(organizacion,Periodo.getPeriodo(mes),HCxMes,anio);
        return 0;
        */
        return hcXorgXpart;
    }


    private void cargarHc(){
        if(this.factorDeEmision==null) actualizarFE();
        this.hcXIntegrantes = CalculadorDeHC.getInstance().calcularHC( this.factorDeEmision,this.valorDA() )/this.integrantes*this.diasAlMes ;
    }

    private double generarReporte(Organizacion organizacion,Periodo mes,double hc,Integer anio){
        HChistorico HChistorico = new HChistorico(this.factorDeEmision.getTipoActividad(),this.factorDeEmision.getTipoConsumo(),anio,mes,hc,organizacion);
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
