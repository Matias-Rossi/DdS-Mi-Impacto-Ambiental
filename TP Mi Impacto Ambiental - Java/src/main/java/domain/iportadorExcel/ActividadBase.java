package domain.iportadorExcel;

public abstract class ActividadBase {

  public ActividadBase(TipoActividad actividad,TipoPeriodicidad periodicidad, String periodicidadDeImputacion ){
  this.actividad=actividad;
  this.periodicidad=periodicidad;
  this.periodicidadDeImputacion=periodicidadDeImputacion;
  System.out.println("SE CREA UNA CLASE");
  System.out.println(actividad);
  }
  private TipoActividad actividad;
  private TipoPeriodicidad periodicidad;
  private String periodicidadDeImputacion;
}
