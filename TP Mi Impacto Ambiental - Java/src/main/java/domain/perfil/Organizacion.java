package domain.perfil;

import domain.importadorExcel.ActividadBase;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Organizacion{
    @Getter
    private List<ActividadBase> actividadesCargadas= new ArrayList<ActividadBase>();
    private String razonSocial;
    private Tipo tipo;
    private List<Area> areas= new ArrayList<Area>();
    private Clasificacion clasificacion;
    private Ubicacion ubicacion;
    private Importador moduloImportador;
    private domain.perfil.Organizacion Organizacion;  //TODO ver que onda this.organizacion

    public Organizacion(Importador moduloImportador) {
        this.moduloImportador = moduloImportador;
    }

    public void darAltaArea(String nombreArea){
        Area nuevaArea = new Area(nombreArea, this.Organizacion );
        this.areas.add(nuevaArea);
    }

    public double calcularHC(){
        return 1.0;
    }

    public void cargarMediciones(String nombreArchivo){
        actividadesCargadas.addAll(moduloImportador.importarDatos(nombreArchivo));
    }
}