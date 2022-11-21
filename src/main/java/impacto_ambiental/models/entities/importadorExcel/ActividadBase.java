package impacto_ambiental.models.entities.importadorExcel;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.perfil.Tipo;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.reportes.Periodo;
import lombok.Getter;
import lombok.Setter;
import impacto_ambiental.models.entities.calculadorHC.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "actividades_bases")
public class ActividadBase extends EntidadPersistente {

  @Column(name = "hc")
  private double hc;

  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_producto_transportado")
  public TipoProductoTransportado tipoProductoTransportado;
  @Column(name = "distancia_media_recorrida")
  private double distanciaMediaRecorrida;
  @Column(name = "peso_total_transportado")
  private double pesoTotalTransportado;
  @Embedded
  private VaraianzaLogistica varianzaDistanciaYPeso;
  @ManyToOne @Setter
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_actividad")
  @Getter
  private TipoActividadDA tipoActividadDA;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipo_consumo")
  @Getter
  private TipoConsumoDA consumo;
  @Column(name = "valor_da")
  private double valorDA;

  @Getter
  @Column(name = "anio")
  private Integer anio;

  @Getter
  @Column(name = "mes")
  private Integer mes;


  @Getter
  @Setter
  @ManyToOne
  @JoinColumn(name = "factorDeEmision_id", referencedColumnName = "id")
  private FactorDeEmision factorDeEmision;

  private void inicializador(TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.tipoActividadDA = actividad;
    this.consumo = consumo;
    this.anio = anio;
    this.mes = mes;
    this.valorDA = valorDA;
    this.hc = -1;
  }
  public ActividadBase(TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes,double  valorDA){
    this.inicializador(actividad,consumo,anio,mes,valorDA);
  }
  public ActividadBase(TipoActividadDA tipoActividad, TipoConsumoDA tipoTransporteUtilizado, Integer anio, Integer mes, double distanciaMediaRecorrida, double pesoTotalTransportado, VaraianzaLogistica varianzaLogistica,TipoProductoTransportado tipoProductoTransportado){
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

  public void calcularHC(){
    if(this.hc<0) this.cargarHc();
//
//
//    if(!this.delAnio(anio))
//      return 0;
//
//    if(mes==0 || this.delMes(mes)) {
//      return this.hc;
//    }
//
//    if(this.delMes(0)) {
//      return this.hc / 12;
//    }
//
//
//
//    return 0;
  }


  public void cargarHc(){

    if(this.factorDeEmision == null) actualizarFE();

    this.hc = CalculadorDeHC.getInstance().calcularHC(this.factorDeEmision,this.valorDA);

    if(this.delMes(0)){
      for(int i = 1; i <= 12; i++){
        HChistorico HChistorico = new HChistorico(this.tipoActividadDA, this.consumo, this.anio, Periodo.getPeriodo(i), this.hc, this.organizacion);
      }
     }
    else{
      HChistorico HChistorico = new HChistorico(this.tipoActividadDA, this.consumo, this.anio, Periodo.getPeriodo(this.mes), this.hc, this.organizacion);
    }
  }




    public void actualizarFE(){
        this.factorDeEmision = CalculadorDeHC.devolverFactorDeEmision(this.generarDatoDeActividad());
    }

  public DatoDeActividad generarDatoDeActividad() {
    return new DatoDeActividad(this.tipoActividadDA, this.consumo, this.valorDA);
  }


}
