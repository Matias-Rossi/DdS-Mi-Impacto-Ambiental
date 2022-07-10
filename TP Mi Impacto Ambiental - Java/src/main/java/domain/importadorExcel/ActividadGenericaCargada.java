package domain.importadorExcel;

import domain.calculadorHC.CalculadorDeHC;
import domain.calculadorHC.DatoDeActividad;
import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Tipo;

public class ActividadGenericaCargada extends ActividadBase {
  public double valorDA;
  public TipoConsumoDA tipoConsumo;
  public ActividadGenericaCargada(CalculadorDeHC calculadorDeHC,TipoActividadDA tipoActividad, TipoConsumoDA tipoConsumo, double valorDA, Integer anio, Integer mes) {
    super(calculadorDeHC,tipoActividad,tipoConsumo,anio,mes);
    System.out.println("SE CREA UNA CLASE");
    System.out.println(calculadorDeHC);
    System.out.println(tipoActividad);
    System.out.println(tipoConsumo);
    System.out.println(valorDA);
    System.out.println(anio);
    System.out.println(mes);
    this.valorDA = valorDA;
    this.tipoConsumo=tipoConsumo;
  }

  }
