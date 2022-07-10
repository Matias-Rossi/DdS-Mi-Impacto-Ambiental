package domain.calculadorHC;

public class FactorDeEmision {
  private TipoActividadDA tipoActividad;
  private TipoConsumoDA tipoConsumo;
  private double factorEmision;


  public FactorDeEmision(TipoActividadDA tipoDeActividad, TipoConsumoDA tipoDeConsumo, double factorEmision) {
    this.tipoActividad = tipoDeActividad;
    this.tipoConsumo = tipoDeConsumo;
    this.factorEmision = factorEmision;
  }

  public TipoActividadDA getTipoActividad() {
    return tipoActividad;
  }

  public TipoConsumoDA getTipoConsumo() {
    return tipoConsumo;
  }

  public double getFactorEmision() {
    return factorEmision;
  }

  public void setFactorEmision(double nuevoFactor){
    this.factorEmision = nuevoFactor;
  }

}

