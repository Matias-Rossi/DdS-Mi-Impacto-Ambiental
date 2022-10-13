package impacto_ambiental.models.entities.notificaciones;

import lombok.Getter;

public class Notificacion {

  @Getter
  private String asunto;

  @Getter
  private String contenido;

  public Notificacion(String asunto, String contenido) {
    this.asunto = asunto;
    this.contenido = contenido;
  }

  public String formatoWhatsapp() {
    return this.asunto + "\n" + "Estimado usuario, " + this.contenido;
  }

}
