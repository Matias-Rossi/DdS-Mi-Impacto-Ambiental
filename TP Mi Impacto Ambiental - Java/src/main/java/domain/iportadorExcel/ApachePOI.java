package domain.iportadorExcel;

import config.Config;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOI {
  //public static List <ActividadBase> listaDeFilas = new ArrayList<ActividadBase>();
  static DataFormatter formatter = new DataFormatter();

  public static void main(String[] args){
    try{
      FileInputStream f = new FileInputStream( Config.RUTA_IMPORTACION +"fechas.xlsx");
      XSSFWorkbook libro = new XSSFWorkbook(f);
      XSSFSheet hoja = libro.getSheetAt(0);
      Iterator<Row> filas = hoja.iterator();
      Iterator<Cell> celdas;

      Row fila;
      Cell celda;

      TipoActividad valorTipoActividad;
      TipoConsumo valorTipoConsumo;
      double valor;
      TipoPeriodicidad valorTipoPeriodicidad;
      String valorPeriodoDeImputacion;
      TipoProductoTransportado valorTipoProductoTransportado;
      TipoTransporteUtilizado valorTipoTransporteUtilizado;
      double valorDistanciaMedia;
      double valorPesoTotal;
      CargaSegunActividad cargaSegunActividad;
      List<CargaSegunActividad> listaDeCargas=new ArrayList<CargaSegunActividad>();



      filas.next();
      filas.next();

      while(filas.hasNext()){
        fila = filas.next();
        celdas = fila.cellIterator();

        celda = celdas.next();

        valorTipoActividad = TipoActividad.valueOf(celda.getStringCellValue());
        celda = celdas.next();
        valorTipoConsumo = TipoConsumo.valueOf(celda.getStringCellValue());

        switch (valorTipoActividad){
          case LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS: {

            for (int n=0;n<4;n++){
              celda = celdas.next();
              switch (valorTipoConsumo){
                case PRODUCTO_TRANSPORTADO:valorTipoProductoTransportado=TipoProductoTransportado.valueOf(celda.getStringCellValue());
                case MEDIO_DE_TRANSPORTE:valorTipoTransporteUtilizado=TipoTransporteUtilizado.valueOf(celda.getStringCellValue());
                case DISTANCIA_MEDIA:valorDistanciaMedia=celda.getNumericCellValue();
                case PESO_TOTAL:valorPesoTotal=celda.getNumericCellValue();
              }
              fila=filas.next();
              celdas = fila.cellIterator();
              celda = celdas.next();
              celda = celdas.next();
              valorTipoConsumo = TipoConsumo.valueOf(celda.getStringCellValue());

            }

          }
          default:{
            celda = celdas.next();
            valor = celda.getNumericCellValue();
            valorTipoProductoTransportado = null;
            valorTipoTransporteUtilizado = null;
            valorDistanciaMedia = 0;
            valorPesoTotal = 0;
          }
        }
        celda = celdas.next();
        valorTipoPeriodicidad = TipoPeriodicidad.valueOf(celda.getStringCellValue());

        celda = celdas.next();
        valorPeriodoDeImputacion = formatter.formatCellValue(celda);

        switch (valorTipoActividad){
          case LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS: cargaSegunActividad=new ActividadLogistica(valorTipoActividad, valorTipoProductoTransportado, valorTipoTransporteUtilizado, valorDistanciaMedia, valorPesoTotal, valorTipoPeriodicidad, valorPeriodoDeImputacion);
          default:cargaSegunActividad=new ActividadGenerica(valorTipoActividad,valorTipoConsumo,valor,valorTipoPeriodicidad,valorPeriodoDeImputacion);
        }

        System.out.println(valorTipoActividad);
        System.out.println(valorTipoConsumo);
        System.out.println(valor);
        System.out.println(valorTipoPeriodicidad);
        System.out.println(valorPeriodoDeImputacion);
        System.out.println(valorTipoProductoTransportado);
        System.out.println(valorTipoTransporteUtilizado);
        System.out.println(valorDistanciaMedia);
        System.out.println(valorPesoTotal);

        listaDeCargas.add(cargaSegunActividad);

      }
      f.close();
    } catch (
        IOException ex){
      System.out.println(ex.getMessage());
    }
  }


}
