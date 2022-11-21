package impacto_ambiental.spark.utils;

// Estas clases existen porque no se pueden jsonizar las que tenemos modeladas, ya que referencian a objetos que
// al mismo tiempo referencian a ellos.

public class LineaJson {
  public int id;
  public String nombre;
  public String tipoTransporte;
  public LineaJson(int id, String nombre, String tipoTransporte) {
    this.id = id;
    this.nombre = nombre;
    this.tipoTransporte = tipoTransporte;
  }
}