package domain.notificaciones;

import lombok.Getter;

public class Contacto {
  @Getter
  PreferenciasContacto preferenciasContacto;

  @Getter
  private String telefono;

  @Getter
  private String email;

  public Contacto(String telefono, String email) {
    this.telefono = telefono;
    this.email = email;
    if (telefono != null) {
      this.preferenciasContacto = PreferenciasContacto.WHATSAPP;
      if(email != null) {
        this.preferenciasContacto = PreferenciasContacto.TODOS;
      }
    } else if (email != null) {
      this.preferenciasContacto = PreferenciasContacto.EMAIL;
    } else {
      throw new IllegalArgumentException("No se puede crear un contacto sin telefono ni email");

    }
  }
}
