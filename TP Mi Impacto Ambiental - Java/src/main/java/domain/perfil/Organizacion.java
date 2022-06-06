package domain.perfil;

import domain.iportadorExcel.CargaSegunActividad;
import domain.ubicacion.Ubicacion;

import java.util.ArrayList;
import java.util.List;

public class Organizacion{
    private List<CargaSegunActividad> actividadesCargadas= new ArrayList<CargaSegunActividad>();
    private String razonSocial;
    private Tipo tipo;
    private List<Area> areas= new ArrayList<Area>();
    private Clasificacion clasificacion;
    private Ubicacion ubicacion;
    private Importador moduloImportador;
    private domain.perfil.Organizacion Organizacion;  //TODO ver que onda this.organizacion

    public void darAltaArea(String nombreArea){
        Area nuevaArea = new Area(nombreArea, this.Organizacion );
        this.areas.add(nuevaArea);
    }

    public double calcularHC(){
        return 1.0;
    }
    public void cargarMediciones(String nombreArchivo){
        actividadesCargadas.addAll(Importador.importarDatos(nombreArchivo));
    }
}