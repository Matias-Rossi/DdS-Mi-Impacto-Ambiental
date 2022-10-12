package proservices.models.entities.servicios.geodds.entidades;

import lombok.Getter;

public class Provincia {
  @Getter
  private int id;
  @Getter
  private String nombre;
  private Pais pais;
}
