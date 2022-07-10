package domain.trayecto;

import domain.calculadorHC.CalculadorDeHC;
import domain.perfil.Organizacion;
import domain.transporte.Transporte;
import domain.ubicacion.Ubicacion;
//import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trayecto {
    private Integer anio;
    private Integer semestre;
    private  Integer diasAlMes;

    private String descripcion;
    private List<Organizacion> organizaciones = new ArrayList<>();
    private List<Tramo> tramos = new ArrayList<Tramo>();
/*
    private Ubicacion fin(){
        return (this.tramos.get(this.tramos.size()-1)).destino();
    }

    private Ubicacion inicio(){
        return (this.tramos.get(0)).origen();
    }

 */
    public double calcularHC(Integer anio, Integer mes, Organizacion organizacion)
    {
        if((!this.organizaciones.contains(organizacion)) || anio!=this.anio) return 0;

        List<Double> mapped = tramos.stream().map(e->e.calcularHC()).collect(Collectors.toList());
        double HCdiario = (mapped.stream().reduce(0.0, (a, b) ->a+b))/organizaciones.size();
        double HCxMes = HCdiario*diasAlMes;
        Integer inicioSem = (this.semestre-1)*6;
        Integer finSem = this.semestre*6;
        if(mes==0) return HCxMes*6;
        if(inicioSem<mes && mes<=finSem*6) return HCxMes;

        return 0;
    }

    public Trayecto(String descripcion, List<Organizacion> organizaciones, Integer diasAlMes,Integer anio,Integer semestre){
        this.diasAlMes = diasAlMes;
        this.anio = anio;
        this.semestre = semestre;
        this.descripcion = descripcion;
        this.organizaciones = organizaciones;
        this.diasAlMes = diasAlMes;
    }

    public void aniadirNuevoTramo(Ubicacion salida, Ubicacion llegada, Transporte transporte, CalculadorDeHC calculadorDeHC){
        this.agregarTramo(new Tramo(salida, llegada, transporte, calculadorDeHC));
    }

    public void agregarTramo(Tramo tramo){
        this.tramos.add(tramo);
        tramo.sumarIntegrante();
    }

    public double calcularDistanciaTotal() {
        double distanciaTotal = 0;
        for (Tramo tramo : this.tramos) {
            distanciaTotal += tramo.getDistancia();
        }
        return distanciaTotal;
    }
}
