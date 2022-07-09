package domain.importadorExcel;

import domain.calculadorHC.DatoDeActividad;
import domain.perfil.Tipo;

public class ActividadGenericaCargada extends ActividadBase {
  double valor;
  TipoConsumo tipoConsumo;
  public ActividadGenericaCargada(TipoActividad tipoActividad, TipoConsumo tipoConsumo, double valor, Integer anio, Integer mes) {
    super(tipoActividad,anio,mes);
    this.valor = valor;
    this.tipoConsumo=tipoConsumo;
  }

  @Override
  public double calcularHC(){
    return 2;
  }

  //@Override
  //public DatoDeActividad generarDatoDeActividad() { }
}