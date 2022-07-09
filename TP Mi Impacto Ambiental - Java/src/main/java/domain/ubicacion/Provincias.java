package domain.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Provincias {
List<MunicipiosODepartamentos> municipiosODepartamentos = new ArrayList<>();
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = municipiosODepartamentos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
