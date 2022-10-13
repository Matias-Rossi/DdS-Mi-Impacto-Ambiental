package impacto_ambiental.models.entities.calculadorHC;

import lombok.Getter;
import lombok.Setter;

public class DatoDeActividad {

  @Getter
  @Setter
  private double valorDatoActividad;
  @Getter
  @Setter
  private TipoActividadDA tipoActividad;
  @Getter
  @Setter
  private TipoConsumoDA tipoConsumo;

  public DatoDeActividad(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valorDatoActividad) {

    this.valorDatoActividad = valorDatoActividad;
    this.tipoActividad = tipoActividad;
    this.tipoConsumo = tipoConsumo;
  }





}
