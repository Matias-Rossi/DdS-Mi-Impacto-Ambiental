package domain.importadorExcel;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Importador;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOI implements Importador {
  static DataFormatter formatter = new DataFormatter();

  public List<ActividadBase> importarDatos(String path) {
    int cantidadDeValoresDeLogistica = 4;
    List<ActividadBase> listaDeCargas = null;
    try {
      FileInputStream excellFile = new FileInputStream(path);
      XSSFWorkbook excell = new XSSFWorkbook(excellFile);
      XSSFSheet hoja = excell.getSheetAt(0);
      Iterator<Row> filas = hoja.iterator();
      Iterator<Cell> celdas;
      Row fila;
      Cell celda;

      Integer anio;
      Integer mes;
      TipoActividadDA valorTipoActividad;
      TipoLogistica valorTipoConsumo;
      double valor = 0;
      TipoPeriodicidad valorTipoPeriodicidad;
      String valorPeriodoDeImputacion;
      TipoProductoTransportado valorTipoProductoTransportado = null;
      TipoConsumoDA valorTipoTransporteUtilizado = null;
      double valorDistanciaMedia = 0;
      double valorPesoTotal = 0;
      ActividadBase actividadCargada;
      listaDeCargas = new ArrayList<ActividadBase>();


      filas.next();
      filas.next();

      while (filas.hasNext()) {
        fila = filas.next();
        celdas = fila.cellIterator();

        celda = celdas.next();

        valorTipoActividad = TipoActividadDA.valueOf(celda.getStringCellValue());
        celda = celdas.next();
        valorTipoConsumo = TipoLogistica.valueOf(celda.getStringCellValue());

        if (valorTipoActividad == TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS) {
          for (int n = 0; n < cantidadDeValoresDeLogistica; n++) {
            celda = celdas.next();
            switch (valorTipoConsumo) {
              case PRODUCTO_TRANSPORTADO: {
                valorTipoProductoTransportado = TipoProductoTransportado.valueOf(celda.getStringCellValue());
                break;
              }
              case MEDIO_DE_TRANSPORTE: {
                valorTipoTransporteUtilizado = TipoConsumoDA.valueOf(celda.getStringCellValue());
                break;
              }
              case DISTANCIA_MEDIA: {
                valorDistanciaMedia = celda.getNumericCellValue();
                break;
              }
              case PESO_TOTAL: {
                valorPesoTotal = celda.getNumericCellValue();
                break;
              }
              default: {
                break;
              }
            }
            if (n < cantidadDeValoresDeLogistica - 1) {
              fila = filas.next();
              celdas = fila.cellIterator();
              celda = celdas.next();
              celda = celdas.next();
              valorTipoConsumo = TipoLogistica.valueOf(celda.getStringCellValue());
            }
          }
        } else {
          celda = celdas.next();
          valor = celda.getNumericCellValue();
        }


        celda = celdas.next();
        valorTipoPeriodicidad = TipoPeriodicidad.valueOf(celda.getStringCellValue());


        celda = celdas.next();
        valorPeriodoDeImputacion = formatter.formatCellValue(celda);
        System.out.println(valorPeriodoDeImputacion);

        anio = this.obtenerAnio(valorPeriodoDeImputacion);
        mes = this.obtenerMes(valorPeriodoDeImputacion,valorTipoPeriodicidad);



        if (valorTipoActividad == TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS)
          actividadCargada = new ActividadLogisticaCargada(valorTipoActividad, valorTipoProductoTransportado, valorTipoTransporteUtilizado, valorDistanciaMedia, valorPesoTotal, anio, mes);
        else
          actividadCargada = new ActividadGenericaCargada(valorTipoActividad, valorTipoConsumo, valor, anio, mes);


        listaDeCargas.add(actividadCargada);



      }
      excellFile.close();
    } catch (
            IOException ex) {
      System.out.println(ex.getMessage());
    }
   return listaDeCargas;
  }
  private Integer obtenerAnio(String fecha){
    return Integer.parseInt(fecha.substring(fecha.length()-4,fecha.length()));
  }
  private Integer obtenerMes(String fecha,TipoPeriodicidad periodo){
    if(periodo==TipoPeriodicidad.ANUAL){
      return 0;
    }else return Integer.parseInt(fecha.substring(0,2));
  }
}
