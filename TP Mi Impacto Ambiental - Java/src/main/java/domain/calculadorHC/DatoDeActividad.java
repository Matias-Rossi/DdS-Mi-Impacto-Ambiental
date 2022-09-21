package domain.calculadorHC;

import lombok.Getter;
import lombok.Setter;

public class DatoDeActividad {

  @Getter
  @Setter
  private double DA;
  @Getter
  @Setter
  private TipoActividadDA tipoActividadDA;
  @Getter
  @Setter
  private TipoConsumoDA tipoConsumoDA;

  public DatoDeActividad(TipoActividadDA tipoActividadDA, TipoConsumoDA tipoConsumoDA, double DA) {

    this.DA = DA;
    this.tipoActividadDA = tipoActividadDA;
    this.tipoConsumoDA = tipoConsumoDA;
  }





}
