package domain.iportadorExcel;

public class ActividadGenerica extends ActividadBase implements CargaSegunActividad{
  double valor;
  TipoConsumo tipoConsumo;
  public ActividadGenerica(TipoActividad tipoActividad,TipoConsumo tipoConsumo,double valor,TipoPeriodicidad tipoPeriodicidad,String periodicidadDeImputacion) {
    super(tipoActividad,tipoPeriodicidad,periodicidadDeImputacion);
    this.valor = valor;
    this.tipoConsumo=tipoConsumo;
  }
  public double calcularHC(){
    return 2;
  }
}