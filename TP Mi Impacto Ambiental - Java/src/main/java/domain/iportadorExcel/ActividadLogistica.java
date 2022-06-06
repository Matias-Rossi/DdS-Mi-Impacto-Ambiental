package domain.iportadorExcel;

public class ActividadLogistica  extends ActividadBase implements CargaSegunActividad{

  TipoProductoTransportado tipoProductoTransportado;
  TipoTransporteUtilizado medioDeTransporte;
  double distanciaMediaRecorrida;
  double pesoTotalTransportado;
  public ActividadLogistica(TipoActividad tipoActividad,TipoProductoTransportado tipoProductoTransportado,TipoTransporteUtilizado tipoTransporteUtilizado,double distanciaMediaRecorrida,double pesoTotalTransportado,TipoPeriodicidad tipoPeriodicidad,String periodicidadDeImputacion) {
    super(tipoActividad,tipoPeriodicidad,periodicidadDeImputacion);
    this.tipoProductoTransportado = tipoProductoTransportado;
    this.medioDeTransporte = tipoTransporteUtilizado;
    this.distanciaMediaRecorrida = distanciaMediaRecorrida;
    this.pesoTotalTransportado =  pesoTotalTransportado;
  }
  public double calcularHC(){
    return 1;
  }
}
