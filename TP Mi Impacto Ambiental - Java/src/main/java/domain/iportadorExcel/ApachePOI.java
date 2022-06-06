package domain.iportadorExcel;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ddf.NullEscherSerializationListener;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static domain.iportadorExcel.TipoActividad.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS;

public class ApachePOI {
  //public static List <ActividadBase> listaDeFilas = new ArrayList<ActividadBase>();
  static DataFormatter formatter = new DataFormatter();

  public static void main(String[] args){
    try{

      FileInputStream f = new FileInputStream( "C:/Users/pedro/OneDrive/Escritorio/file/fechas.xlsx");
      XSSFWorkbook libro = new XSSFWorkbook(f);
      XSSFSheet hoja = libro.getSheetAt(0);
      Iterator<Row> filas = hoja.iterator();
      Iterator<Cell> celdas;

      Row fila;
      Cell celda;

      TipoActividad valorTipoActividad;
      TipoConsumo valorTipoConsumo;
      double valor=0;
      TipoPeriodicidad valorTipoPeriodicidad;
      String valorPeriodoDeImputacion;
      TipoProductoTransportado valorTipoProductoTransportado=null;
      TipoTransporteUtilizado valorTipoTransporteUtilizado=null;
      double valorDistanciaMedia=0;
      double valorPesoTotal=0;
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

        if(valorTipoActividad==LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS){
          for (int n=0;n<4;n++){
            celda = celdas.next();
            switch (valorTipoConsumo){
              case PRODUCTO_TRANSPORTADO:{
                valorTipoProductoTransportado=TipoProductoTransportado.valueOf(celda.getStringCellValue());
                break;
              }
              case MEDIO_DE_TRANSPORTE:{
                valorTipoTransporteUtilizado=TipoTransporteUtilizado.valueOf(celda.getStringCellValue());
                break;
              }
              case DISTANCIA_MEDIA:{
                valorDistanciaMedia=celda.getNumericCellValue();
                break;
              }
              case PESO_TOTAL:{
                valorPesoTotal=celda.getNumericCellValue();
                break;
              }
              default: {
                break;
              }
            }
            if(n<3){
              fila=filas.next();
              celdas = fila.cellIterator();
              celda = celdas.next();
              celda = celdas.next();
              valorTipoConsumo = TipoConsumo.valueOf(celda.getStringCellValue());
            }
          }
        }else{
          celda = celdas.next();
          valor = celda.getNumericCellValue();
          valorTipoProductoTransportado = null;
          valorTipoTransporteUtilizado = null;
          valorDistanciaMedia = 0;
          valorPesoTotal = 0;
        }


        celda = celdas.next();
        valorTipoPeriodicidad = TipoPeriodicidad.valueOf(celda.getStringCellValue());

        celda = celdas.next();
        valorPeriodoDeImputacion = formatter.formatCellValue(celda);


        if(valorTipoActividad==LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS)
          cargaSegunActividad=new ActividadLogistica(valorTipoActividad, valorTipoProductoTransportado, valorTipoTransporteUtilizado, valorDistanciaMedia, valorPesoTotal, valorTipoPeriodicidad, valorPeriodoDeImputacion);
          else
            cargaSegunActividad=new ActividadGenerica(valorTipoActividad,valorTipoConsumo,valor,valorTipoPeriodicidad,valorPeriodoDeImputacion);

        listaDeCargas.add(cargaSegunActividad);
      }
      f.close();
    } catch (
        IOException ex){
      System.out.println(ex.getMessage());
    }
  }


}
