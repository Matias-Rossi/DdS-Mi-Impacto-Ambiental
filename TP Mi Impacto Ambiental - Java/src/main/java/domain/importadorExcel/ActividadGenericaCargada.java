package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Tipo;

public class ActividadGenericaCargada extends ActividadBase {
  public ActividadGenericaCargada(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valorDA, Integer anio, Integer mes) {
    super(tipoActividad,tipoConsumo,anio,mes,valorDA);
  }

  }
