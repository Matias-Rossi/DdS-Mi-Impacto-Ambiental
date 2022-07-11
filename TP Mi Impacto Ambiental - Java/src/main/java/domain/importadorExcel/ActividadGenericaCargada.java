package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Tipo;

public class ActividadGenericaCargada extends ActividadBase {
  public double valorDA;
  public ActividadGenericaCargada(CalculadorDeHC calculadorDeHC,TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valorDA, Integer anio, Integer mes) {
    super(calculadorDeHC,tipoActividad,tipoConsumo,anio,mes,valorDA);
    this.valorDA = valorDA;
  }

  }
