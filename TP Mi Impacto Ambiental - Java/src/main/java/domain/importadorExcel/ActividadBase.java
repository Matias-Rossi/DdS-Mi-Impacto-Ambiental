package domain.importadorExcel;

import domain.calculadorHC.*;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "actividades_bases")
public class ActividadBase extends EntidadPersistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_producto_transportado")
  public TipoProductoTransportado tipoProductoTransportado;
  @Column(name = "distancia_media_recorrida")
  private double distanciaMediaRecorrida;
  @Column(name = "peso_total_transportado")
  private double pesoTotalTransportado;
  @Embedded
  private VaraianzaLogistica varianzaDistanciaYPeso;
  @ManyToOne
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_actividad")
  private TipoActividadDA tipoActividadDA;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_consumo")
  private TipoConsumoDA consumo;
  @Column(name = "valor_da")
  private double valorDA;
  @Column(name = "anio")
  private Integer anio;
  @Column(name = "mes")
  private Integer mes;

  @ManyToOne
  @JoinColumn(name = "factorDeEmision_id", referencedColumnName = "id")
  private FactorDeEmision factorDeEmision;

  private void inicializador(TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.tipoActividadDA = actividad;
    this.consumo = consumo;
    this.anio = anio;
    this.mes = mes;
    this.valorDA = valorDA;
  }
  public ActividadBase(TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.inicializador(tipoActividadDA,consumo,anio,mes,valorDA);
  }
  public ActividadBase(TipoActividadDA tipoActividad, TipoConsumoDA tipoTransporteUtilizado,Integer anio, Integer mes, double distanciaMediaRecorrida, double pesoTotalTransportado, VaraianzaLogistica varianzaLogistica,TipoProductoTransportado tipoProductoTransportado){
    this.inicializador(tipoActividad,tipoTransporteUtilizado,anio,mes, distanciaMediaRecorrida * pesoTotalTransportado * varianzaLogistica.getVaraianzaLogistica());
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
    this.varianzaDistanciaYPeso = varianzaLogistica;
  }

  public ActividadBase() {

  }


  public boolean delMes(Integer mesAProbar){
    return mesAProbar.equals(this.mes);
  }
  public boolean delAnio(Integer anioAProbar){
    return anioAProbar.equals(this.anio);
  }

  public double calcularHC(Integer anio,Integer mes) {
    if(this.factorDeEmision==null) actualizarFE();
    double HC =CalculadorDeHC.getInstance().calcularHC(this.factorDeEmision,this.valorDA);
    if(!this.delAnio(anio)) return 0;

    if(mes==0 || this.delMes(mes)) return HC;

    if(this.delMes(0)) return HC / 12;

    return 0;
  }

    public void actualizarFE(){
        this.factorDeEmision = CalculadorDeHC.devolverFactorDeEmision(this.generarDatoDeActividad());
    }

  public DatoDeActividad generarDatoDeActividad() {
    return new DatoDeActividad(this.tipoActividadDA, this.consumo, this.valorDA);
  }


}
