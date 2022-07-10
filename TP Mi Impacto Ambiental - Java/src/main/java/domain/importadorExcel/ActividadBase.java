package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;

public abstract class ActividadBase {

  public ActividadBase(TipoActividadDA actividad, TipoConsumoDA consumo, Integer anio, Integer mes ){
  this.tipoActividadDA=actividad;
  this.consumo=consumo;
  this.anio=anio;
  this.mes=mes;
  }
  private TipoActividadDA tipoActividadDA;
  private TipoConsumoDA consumo;
  private double valorDA;
  private Integer anio;
  private Integer mes;
  CalculadorDeHC calculadorDeHC;


  public boolean delMes(Integer mes){
    return mes==this.mes;
  }
  public boolean delAnio(Integer anio){
    return anio==this.anio;
  }

  public double calcularHC(Integer anio,Integer mes) {
    double HC =this.calculadorDeHC.calcularHC(this.generarDatoDeActividad(this.tipoActividadDA,this.consumo, this.valorDA));
    if(!this.delAnio(anio)) return 0;
    if(mes==0 || this.delMes(mes)) return HC;
    if(this.delMes(0)) return HC/12;
    return 0;
  }

  public DatoDeActividad generarDatoDeActividad(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valorDA) {
    return new DatoDeActividad(tipoActividad, tipoConsumo, valorDA);
  }


}
