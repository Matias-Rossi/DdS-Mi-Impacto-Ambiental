package domain.ubicacion;

import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "provincias")
public class Provincias extends EntidadPersistente {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "provincia")
    private Provincia provincia;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "provincia")
    List<MunicipiosODepartamentos> municipiosODepartamentos = new ArrayList<>();

    public Provincias() {

    }

    public MunicipiosODepartamentos crearMunicipio(String municipioNombre){
        MunicipiosODepartamentos muni = new MunicipiosODepartamentos(this,municipioNombre);
        this.municipiosODepartamentos.add(muni);
        return muni;
    }

    public Provincias(Provincia provincia){
        this.provincia=provincia;

    }
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = municipiosODepartamentos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
