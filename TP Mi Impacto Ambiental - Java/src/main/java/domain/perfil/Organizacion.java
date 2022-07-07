package domain.perfil;

import domain.importadorExcel.ActividadBase;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public double calcularHC(Integer anio,Integer mes){
        return this.calcularHCConsumos(anio,mes)+this.calcularHCViajes(anio,mes);
    }
    private double calcularHCConsumos(Integer anio,Integer mes){
        Stream<ActividadBase> filtrada;
        filtrada=actividadesCargadas.stream().filter(e->e.delAnio(anio));
        if(mes!=0){
            filtrada=filtrada.filter(e->e.delMes(mes));
        }
        List<Double> mapped = filtrada.map(e->e.calcularHC()).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
    private double calcularHCViajes(Integer anio,Integer mes){
        return 1.0;
    }

    public void cargarMediciones(String nombreArchivo){
        actividadesCargadas.addAll(moduloImportador.importarDatos(nombreArchivo));
    }
}