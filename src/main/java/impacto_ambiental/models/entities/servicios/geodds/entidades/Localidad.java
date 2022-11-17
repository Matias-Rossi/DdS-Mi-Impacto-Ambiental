package impacto_ambiental.models.entities.servicios.geodds.entidades;

import lombok.Getter;

public class Localidad {
  @Getter
  private int id;
  @Getter
  private String nombre;
  private String codPostal;
  private Municipio municipio;
}
