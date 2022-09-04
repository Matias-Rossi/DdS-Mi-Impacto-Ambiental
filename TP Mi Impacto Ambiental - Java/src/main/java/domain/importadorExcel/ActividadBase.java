package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "actividadesBases")
public class ActividadBase extends EntidadPersistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoProductoTransportado")
  public TipoProductoTransportado tipoProductoTransportado;
  @Column(name = "distanciaMediaRecorrida")
  private static double distanciaMediaRecorrida;
  @Column(name = "pesoTotalTransportado")
  private static double pesoTotalTransportado;
  @Embedded
  private static VaraianzaLogistica varianzaDistanciaYPeso;
  @ManyToOne
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoActividad")
  private TipoActividadDA tipoActividadDA;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoConsumo")
  private TipoConsumoDA consumo;
  @Column(name = "valorDA")
  private double valorDA;
  @Column(name = "anio")
  private Integer anio;
  @Column(name = "mes")
  private Integer mes;
  @Transient
  private CalculadorDeHC calculadorDeHC;

  private void inicializador(CalculadorDeHC calculadorDeHC,TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.calculadorDeHC = calculadorDeHC;
    this.tipoActividadDA = actividad;
    this.consumo = consumo;
    this.anio = anio;
    this.mes = mes;
    this.valorDA = valorDA;
  }
  public ActividadBase(CalculadorDeHC calculadorDeHC,TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.inicializador(calculadorDeHC,tipoActividadDA,consumo,anio,mes,valorDA);
  }
  public ActividadBase(CalculadorDeHC calculadorDeHC,TipoActividadDA tipoActividad, TipoConsumoDA tipoTransporteUtilizado,Integer anio, Integer mes, double distanciaMediaRecorrida, double pesoTotalTransportado, VaraianzaLogistica varianzaLogistica,TipoProductoTransportado tipoProductoTransportado){
    this.inicializador(calculadorDeHC,tipoActividad,tipoTransporteUtilizado,anio,mes, distanciaMediaRecorrida * pesoTotalTransportado * varianzaLogistica.getVaraianzaLogistica());
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
    double HC =this.calculadorDeHC.calcularHC(this.generarDatoDeActividad());
    if(!this.delAnio(anio)) return 0;

    if(mes==0 || this.delMes(mes)) return HC;

    if(this.delMes(0)) return HC / 12;

    return 0;
  }

  public DatoDeActividad generarDatoDeActividad() {
    return new DatoDeActividad(this.tipoActividadDA, this.consumo, this.valorDA);
  }


}
