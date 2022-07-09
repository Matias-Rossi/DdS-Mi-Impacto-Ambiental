package domain.importadorExcel;

import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Tipo;

public class ActividadGenericaCargada extends ActividadBase {
  double valor;
  TipoConsumoDA tipoConsumo;
  public ActividadGenericaCargada(TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valor, Integer anio, Integer mes) {
    super(tipoActividad,anio,mes);
    this.valor = valor;
    this.tipoConsumo=tipoConsumo;
  }

  @Override
  public double calcularHC(){
    return 2;
  }

  @Override
  public DatoDeActividad generarDatoDeActividad(TipoActividadDA tipoActividad,TipoConsumoDA tipoConsumo,double valor) {

    return new DatoDeActividad(tipoActividad, tipoConsumo,valor);
  }
}