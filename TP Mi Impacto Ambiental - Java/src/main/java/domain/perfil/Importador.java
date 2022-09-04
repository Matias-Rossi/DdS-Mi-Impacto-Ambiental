package domain.perfil;

import domain.calculadorHC.CalculadorDeHC;
import domain.importadorExcel.ActividadBase;
import domain.importadorExcel.ApachePOI;

import java.util.Collections;
import java.util.List;


public interface Importador {
    List<ActividadBase> importarDatos(String nombreArchivo, CalculadorDeHC calculadorDeHC,Organizacion organizacion);//{
        //List<ActividadBase> lista = Collections.<ActividadBase>emptyList();
        //ApachePOI.importarCargas(nombreArchivo);
        //return lista;
    //};
}
