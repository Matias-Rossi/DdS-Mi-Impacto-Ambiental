package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;

public class ActividadLogisticaCargada extends ActividadBase {

  TipoProductoTransportado tipoProductoTransportado;
  TipoConsumoDA medioDeTransporte;
  double distanciaMediaRecorrida;
  double pesoTotalTransportado;
  double varianzaDistanciaYPeso = 0.5;
  CalculadorDeHC calculadorDeHC;

  public ActividadLogisticaCargada(TipoActividadDA tipoActividad, TipoProductoTransportado tipoProductoTransportado, TipoConsumoDA tipoTransporteUtilizado, double distanciaMediaRecorrida, double pesoTotalTransportado, Integer anio, Integer mes) {
    super(tipoActividad,anio,mes);
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.medioDeTransporte = tipoTransporteUtilizado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
  }


  @Override
  public double calcularHC(){
    calculadorDeHC
    return 1;
  }

  @Override
  public DatoDeActividad generarDatoDeActividad(TipoActividadDA tipoActividad, TipoConsumoDA tipoTransporteUtilizado, double distanciaMediaRecorrida, double pesoTotalTransportado, double varianzaDistanciaYPeso) {
    double valor = distanciaMediaRecorrida * pesoTotalTransportado * varianzaDistanciaYPeso;
    return new DatoDeActividad(tipoActividad, tipoTransporteUtilizado, valor);
  }

}
