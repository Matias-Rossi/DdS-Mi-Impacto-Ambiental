package impacto_ambiental.models.entities.importadorExcel;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.entities.perfil.Importador;
import impacto_ambiental.models.entities.perfil.Organizacion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//TODO Varios arreglos pendientes

public final class ApachePOI implements Importador {
  static DataFormatter formatter = new DataFormatter();
    static ApachePOI instance;
  public static ApachePOI getInstance() {
    if (instance == null) {
      instance = new ApachePOI();
    }
    return instance;
  }
  public List<ActividadBase> importarDatos(String path, Organizacion organizacion) {
    final int cantidadDeValoresDeLogistica = 4;
    List<ActividadBase> listaDeCargas = new ArrayList<>();
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
      TipoConsumoDA valorTipoConsumo;
      IndiceLogistica valorIndiceLogistica;
      double valor = 0;
      TipoPeriodicidad valorTipoPeriodicidad;
      String valorPeriodoDeImputacion;
      TipoProductoTransportado valorTipoProductoTransportado = null;
      TipoConsumoDA valorTipoTransporteUtilizado = null;
      double valorDistanciaMedia = 0.0;
      double valorPesoTotal = 0.0;
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


        if (valorTipoActividad == TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS) {
          valorTipoConsumo = TipoConsumoDA.UTILITARIO_LIVIANO;
          valorTipoProductoTransportado = TipoProductoTransportado.INSUMOS;
          for (int n = 0; n < cantidadDeValoresDeLogistica; n++) {
            valorIndiceLogistica = IndiceLogistica.valueOf(celda.getStringCellValue());
            celda = celdas.next();
            switch (valorIndiceLogistica) {
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
              celdas.next();
              celda = celdas.next();

            }
          }
        } else {
          valorTipoConsumo = TipoConsumoDA.valueOf(celda.getStringCellValue());
          celda = celdas.next();
          valor = celda.getNumericCellValue();
        }


        celda = celdas.next();
        valorTipoPeriodicidad = TipoPeriodicidad.valueOf(celda.getStringCellValue());


        celda = celdas.next();
        valorPeriodoDeImputacion = formatter.formatCellValue(celda);

        anio = ApachePOI.getInstance().obtenerAnio(valorPeriodoDeImputacion);
        mes = ApachePOI.getInstance().obtenerMes(valorPeriodoDeImputacion,valorTipoPeriodicidad);



        if (valorTipoActividad == TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS){
          actividadCargada = new ActividadBase(valorTipoActividad, valorTipoTransporteUtilizado, anio, mes,valorDistanciaMedia, valorPesoTotal,new VaraianzaLogistica(0.5),valorTipoProductoTransportado);

        }else
          actividadCargada = new ActividadBase(valorTipoActividad, valorTipoConsumo, anio, mes, valor);

        actividadCargada.setOrganizacion(organizacion);

        listaDeCargas.add(actividadCargada);



      }
      excellFile.close();
    } catch (
            IOException ex) {
      System.out.println(ex.getMessage());
    }
   return listaDeCargas;
  }
  public Integer obtenerAnio(String fecha){
    return Integer.parseInt(fecha.substring(fecha.length()-4,fecha.length()));
  }
  public Integer obtenerMes(String fecha,TipoPeriodicidad periodo){
    if(periodo==TipoPeriodicidad.ANUAL){
      return 0;
    }else return Integer.parseInt(fecha.substring(0,2));
  }
}
