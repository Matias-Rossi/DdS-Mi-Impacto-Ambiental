package domain.importadorExcel;

public abstract class ActividadBase {

  public ActividadBase(TipoActividad actividad,Integer anio, Integer mes ){
  this.actividad=actividad;
  this.anio=anio;
  this.mes=mes;
  System.out.println("SE CREA UNA CLASE");
  System.out.println(actividad);
  }
  private TipoActividad actividad;
  private Integer anio;
  private Integer mes;

  public boolean delMes(Integer mes){
    return mes==this.mes;
  }
  public boolean delAnio(Integer anio){
    return anio==this.anio;
  }
  public double calcularHC(){
    return 0;
  }
}
