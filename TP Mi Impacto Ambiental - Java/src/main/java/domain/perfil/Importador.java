package domain.perfil;

import domain.iportadorExcel.ApachePOI;
import domain.iportadorExcel.ActividadCargada;

import java.util.Collections;
import java.util.List;


public interface Importador {
    static List<ActividadCargada> importarDatos(String nombreArchivo){
        List<ActividadCargada> lista = Collections.<ActividadCargada>emptyList();
        ApachePOI.importarCargas(nombreArchivo);
        return lista;
    };
}
