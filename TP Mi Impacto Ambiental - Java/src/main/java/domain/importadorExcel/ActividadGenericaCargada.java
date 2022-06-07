package domain.importadorExcel;

public class ActividadGenericaCargada extends ActividadBase implements ActividadCargada {
  double valor;
  TipoConsumo tipoConsumo;
  public ActividadGenericaCargada(TipoActividad tipoActividad, TipoConsumo tipoConsumo, double valor, TipoPeriodicidad tipoPeriodicidad, String periodicidadDeImputacion) {
    super(tipoActividad,tipoPeriodicidad,periodicidadDeImputacion);
    this.valor = valor;
    this.tipoConsumo=tipoConsumo;
    System.out.println(valor);
    System.out.println(tipoConsumo);
  }
  public double calcularHC(){
    return 2;
  }
}