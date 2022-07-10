package domain.ubicacion;

import domain.perfil.Clasificacion;
import domain.perfil.Importador;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MunicipiosODepartamentos {
    List<Organizacion> organizaciones = new ArrayList<Organizacion>();

    Provincia provincia;
    private String municipioOLocalidad;

    public void crearOrganizacion(Organizacion organizacion,Importador moduloImportador,String razonSocial,Tipo tipo, Clasificacion clasificacion,String loc,String cp,String cal,int num){
        Ubicacion ubicacion=new Ubicacion(this.provincia,this.municipioOLocalidad,loc,cp,cal,num);
        organizaciones.add(new Organizacion(moduloImportador,ubicacion,razonSocial,tipo,clasificacion));
    }

    public MunicipiosODepartamentos(Provincia provincia){
        this.provincia=provincia;
    }
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = organizaciones.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
