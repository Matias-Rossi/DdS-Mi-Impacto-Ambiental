package domain.calculadorHC;

import domain.persistenceExtend.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "factoresDeEmision")
public class FactorDeEmision extends EntidadPersistente {
  @Transient
  private CalculadorDeHC calculadorDeHC;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoActividad")
  private TipoActividadDA tipoActividad;
  @Enumerated(EnumType.STRING)
  @Column(name = "tipoConsumo")
  private TipoConsumoDA tipoConsumo;
  @Column(name = "factor")
  private double factorEmision;


  public FactorDeEmision(TipoActividadDA tipoDeActividad, TipoConsumoDA tipoDeConsumo, double factorEmision) {
    this.tipoActividad = tipoDeActividad;
    this.tipoConsumo = tipoDeConsumo;
    this.factorEmision = factorEmision;
    this.calculadorDeHC = CalculadorDeHC.getInstance();
  }

  public FactorDeEmision() {

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

