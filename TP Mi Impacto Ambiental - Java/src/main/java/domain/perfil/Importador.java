package domain.perfil;

import domain.importadorExcel.ActividadBase;
import domain.importadorExcel.ApachePOI;

import java.util.Collections;
import java.util.List;


public interface Importador {
    List<ActividadBase> importarDatos(String nombreArchivo);//{
        //List<ActividadBase> lista = Collections.<ActividadBase>emptyList();
        //ApachePOI.importarCargas(nombreArchivo);
        //return lista;
    //};
}
