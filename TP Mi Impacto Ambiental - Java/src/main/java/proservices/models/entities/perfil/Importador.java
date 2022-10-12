package proservices.models.entities.perfil;

import proservices.models.entities.importadorExcel.ActividadBase;

import java.util.List;


public interface Importador {
    List<ActividadBase> importarDatos(String nombreArchivo, Organizacion organizacion);//{
        //List<ActividadBase> lista = Collections.<ActividadBase>emptyList();
        //ApachePOI.importarCargas(nombreArchivo);
        //return lista;
    //};
}
