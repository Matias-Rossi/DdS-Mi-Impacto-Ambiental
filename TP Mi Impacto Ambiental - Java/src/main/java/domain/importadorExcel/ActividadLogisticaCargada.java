package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;

public class ActividadLogisticaCargada extends ActividadBase {

  public TipoProductoTransportado tipoProductoTransportado;
  public TipoConsumoDA medioDeTransporte;
  public TipoActividadDA tipoActividad;
  private static double distanciaMediaRecorrida;
  private static double pesoTotalTransportado;
  private static VaraianzaLogistica varianzaDistanciaYPeso;
  public ActividadLogisticaCargada(TipoActividadDA tipoActividad, TipoProductoTransportado tipoProductoTransportado, TipoConsumoDA tipoTransporteUtilizado, double distanciaMediaRecorrida, double pesoTotalTransportado, Integer anio, Integer mes,VaraianzaLogistica varianzaLogistica) {
    super(tipoActividad,tipoTransporteUtilizado,anio,mes, distanciaMediaRecorrida * pesoTotalTransportado * varianzaLogistica.getVaraianzaLogistica());
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
    this.varianzaDistanciaYPeso = varianzaLogistica;
  }

}
