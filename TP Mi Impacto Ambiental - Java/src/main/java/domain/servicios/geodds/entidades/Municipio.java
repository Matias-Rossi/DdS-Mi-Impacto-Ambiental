package domain.servicios.geodds.entidades;

import lombok.Getter;

public class Municipio {
  @Getter
  private int id;
  @Getter
  private String nombre;
  private Provincia provincia;
}
