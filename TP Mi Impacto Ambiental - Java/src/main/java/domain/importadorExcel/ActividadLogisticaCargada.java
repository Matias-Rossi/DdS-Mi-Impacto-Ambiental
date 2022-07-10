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


  public ActividadLogisticaCargada(CalculadorDeHC calculadorDeHC,TipoActividadDA tipoActividad, TipoProductoTransportado tipoProductoTransportado, TipoConsumoDA tipoTransporteUtilizado, double distanciaMediaRecorrida, double pesoTotalTransportado, Integer anio, Integer mes) {
    super(calculadorDeHC,tipoActividad,tipoTransporteUtilizado,anio,mes);
    System.out.println("SE CREA UNA CLASE");
    System.out.println(calculadorDeHC);
    System.out.println(tipoActividad);
    System.out.println(tipoProductoTransportado);
    System.out.println(tipoTransporteUtilizado);
    System.out.println(distanciaMediaRecorrida);
    System.out.println(pesoTotalTransportado);
    System.out.println(anio);
    System.out.println(mes);
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
  }

}
