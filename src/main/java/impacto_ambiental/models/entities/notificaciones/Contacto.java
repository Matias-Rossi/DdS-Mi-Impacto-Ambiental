package impacto_ambiental.models.entities.notificaciones;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.EntidadPersistente;
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
  public PreferenciasContacto preferenciasContacto;

  @Getter
  @Column(name = "telefono")
  public String telefono;

  @Getter
  @Column(name = "email")
  public String email;

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
