package domain.perfil;

import domain.importadorExcel.ApachePOI;
import domain.importadorExcel.ActividadCargada;

import java.util.Collections;
import java.util.List;


public interface Importador {
    static List<ActividadCargada> importarDatos(String nombreArchivo){
        List<ActividadCargada> lista = Collections.<ActividadCargada>emptyList();
        ApachePOI.importarCargas(nombreArchivo);
        return lista;
    };
}
