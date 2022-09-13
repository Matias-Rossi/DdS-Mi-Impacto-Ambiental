package domain.ubicacion;

import domain.perfil.Clasificacion;
import domain.perfil.Importador;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "municipiosODepartamentos")
public class MunicipiosODepartamentos extends EntidadPersistente {

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "municipioODepartamento")
    List<Organizacion> organizaciones = new ArrayList<Organizacion>();

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    Provincias provincia;
    @Getter
    @Column(name = "muncipioOLocalidad")
    private String municipioOLocalidad;

    public MunicipiosODepartamentos() {

    }

    public Organizacion crearOrganizacion(Importador moduloImportador,String razonSocial,Tipo tipo, Clasificacion clasificacion,String loc,String cp,String cal,int num){
        Ubicacion ubicacion=new Ubicacion(this,loc,cp,cal,num);
        Organizacion org = new Organizacion(moduloImportador,ubicacion,razonSocial,tipo,clasificacion);
        organizaciones.add(org);
        return org;
    }

    public MunicipiosODepartamentos(Provincias provincia, String municipioOLocalidad){
        this.provincia=provincia;
        this.municipioOLocalidad=municipioOLocalidad;
    }
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = organizaciones.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }

    public Provincia getProvincia() {
        return provincia.getProvincia();
    }

}
