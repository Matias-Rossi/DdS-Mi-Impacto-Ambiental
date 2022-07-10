package domain.ubicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Provincias {

    private Provincia provincia;
    List<MunicipiosODepartamentos> municipiosODepartamentos = new ArrayList<>();

    public void crearMunicipio(MunicipiosODepartamentos municipiosODepartamentos){
        this.municipiosODepartamentos.add(new MunicipiosODepartamentos(this.provincia));
    }

    public Provincias(Provincia provincia){
        this.provincia=provincia;

    }
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = municipiosODepartamentos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
