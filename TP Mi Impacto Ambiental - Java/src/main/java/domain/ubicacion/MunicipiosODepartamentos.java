package domain.ubicacion;

import domain.perfil.Organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MunicipiosODepartamentos {
    List<Organizacion> organizaciones = new ArrayList<Organizacion>();
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = organizaciones.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
