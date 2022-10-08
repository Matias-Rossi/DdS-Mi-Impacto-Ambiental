package domain.notificaciones;

import domain.perfil.Organizacion;
import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "contactos")
public class Contacto extends EntidadPersistente {

  @ManyToOne
  @JoinColumn(name = "organizacion_id", referencedColumnName = "id")
  private Organizacion organizacion;

  @Getter
  @Enumerated(EnumType.STRING)
  @Column(name = "preferencia")
  PreferenciasContacto preferenciasContacto;

  @Getter
  @Column(name = "telefono")
  private String telefono;

  @Getter
  @Column(name = "email")
  private String email;

  public Contacto(String telefono, String email, Organizacion organizacion) {
    this.organizacion= organizacion;
    this.telefono = telefono;
    this.email = email;

    if(email != null && telefono != null)
      this.preferenciasContacto = PreferenciasContacto.TODOS;

    else if(telefono != null)
      this.preferenciasContacto = PreferenciasContacto.WHATSAPP;

    else if(email != null)
      this.preferenciasContacto = PreferenciasContacto.EMAIL;

    else
      throw new IllegalArgumentException("No se puede crear un contacto sin telefono ni email");
  }

  public Contacto() {

  }
}
