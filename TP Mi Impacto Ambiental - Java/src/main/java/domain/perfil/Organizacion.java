package domain.perfil;

import domain.calculadorHC.CalculadorDeHC;
import domain.importadorExcel.ActividadBase;
import domain.notificaciones.Contacto;
import domain.ubicacion.Ubicacion;
import lombok.Getter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Organizacion{
    @Getter
    public List<ActividadBase> actividadesCargadas= new ArrayList<ActividadBase>();
    private String razonSocial;
    private Tipo tipo;
    private List<Area> areas= new ArrayList<Area>();
    private Clasificacion clasificacion;
    private Ubicacion ubicacion;
    private Importador moduloImportador;
    private domain.perfil.Organizacion Organizacion;
    @Getter
    private List<Contacto> contactos = new ArrayList<Contacto>();

    public Organizacion(Importador moduloImportador, Ubicacion ubicacion,String razonSocial,Tipo tipo,Clasificacion clasificacion) {
        this.ubicacion=ubicacion;
        this.razonSocial=razonSocial;
        this.tipo=tipo;
        this.clasificacion=clasificacion;
        this.moduloImportador = moduloImportador;
    }

    public Area darAltaArea(String nombreArea){
        Area nuevaArea = new Area(nombreArea, this );
        this.areas.add(nuevaArea);
        return nuevaArea;
    }

    public double calcularHC(Integer anio,Integer mes){
        return this.calcularHCConsumos(anio,mes)+this.calcularHCViajes(anio,mes);
    }
    private double calcularHCConsumos(Integer anio,Integer mes){
        List<Double> mapped = actividadesCargadas.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
    private double calcularHCViajes(Integer anio,Integer mes){
        List<Double> mapped = areas.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }

    public void cargarMediciones(String nombreArchivo, CalculadorDeHC calculadorDeHC){
        actividadesCargadas.addAll(moduloImportador.importarDatos(nombreArchivo,calculadorDeHC));
    }

    public void agregarContacto(String telefono, String email){
        Contacto nuevoContacto = new Contacto(telefono, email);
        this.contactos.add(nuevoContacto);
    }
}