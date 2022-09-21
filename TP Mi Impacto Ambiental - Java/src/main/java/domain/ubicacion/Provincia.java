package domain.ubicacion;

import domain.persistenceExtend.EntidadPersistente;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.repositorios.RepositorioMunicipiosODepartamentos;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "provincias")
public class Provincia extends EntidadPersistente {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private NombreProvincia nombreProvincia;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "provincia")
    List<MunicipiosODepartamentos> municipiosODepartamentos = new ArrayList<>();

    public Provincia() {

    }

    public MunicipiosODepartamentos crearMunicipio(String municipioNombre){
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        MunicipiosODepartamentos muni = repositorioMunicipiosODepartamentos.getMunicipio(this, municipioNombre);
        this.municipiosODepartamentos.add(muni);
        return muni;
    }

    public Provincia(NombreProvincia nombreProvincia){
        this.nombreProvincia = nombreProvincia;
    }


    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = municipiosODepartamentos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
