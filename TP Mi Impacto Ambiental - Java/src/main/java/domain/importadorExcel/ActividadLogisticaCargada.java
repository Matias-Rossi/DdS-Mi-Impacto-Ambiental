package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;

public class ActividadLogisticaCargada extends ActividadBase {

  public TipoProductoTransportado tipoProductoTransportado;
  public TipoConsumoDA medioDeTransporte;
  public TipoActividadDA tipoActividad;
  private double distanciaMediaRecorrida;
  private double pesoTotalTransportado;
  private double varianzaDistanciaYPeso = 0.5;
  public double valorDA = distanciaMediaRecorrida * pesoTotalTransportado * varianzaDistanciaYPeso;

  CalculadorDeHC calculadorDeHC;

  public ActividadLogisticaCargada(TipoActividadDA tipoActividad, TipoProductoTransportado tipoProductoTransportado, TipoConsumoDA tipoTransporteUtilizado, double distanciaMediaRecorrida, double pesoTotalTransportado, Integer anio, Integer mes) {
    super(tipoActividad,tipoTransporteUtilizado,anio,mes);
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
  }

}
