package impacto_ambiental.spark.utils;

import impacto_ambiental.models.entities.transporte.Parada;

public class ParadaJson {
  public int id;
  public String nombre;
  public int index;

  public ParadaJson(Parada parada) {
    this.id = parada.getId();
    this.index = parada.getIndex();
    if(parada.getNombre() == null || parada.getNombre() == "") {
      this.nombre = parada.getUbicacion().print();
    } else {
      this.nombre = parada.getNombre();
    }
  }
}
