package domain.perfil;

import domain.iportadorExcel.ApachePOI;
import domain.iportadorExcel.CargaSegunActividad;

import java.util.Collections;
import java.util.List;


public interface Importador {
    static List<CargaSegunActividad> importarDatos(String nombreArchivo){
        List<CargaSegunActividad> lista = Collections.<CargaSegunActividad>emptyList();
        ApachePOI.importarCargas(nombreArchivo);
        return lista;
    };
}
