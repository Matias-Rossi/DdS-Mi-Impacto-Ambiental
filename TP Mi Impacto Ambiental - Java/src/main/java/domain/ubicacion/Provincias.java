package domain.ubicacion;

import domain.persistenceExtend.EntidadPersistente;
import domain.persistenceExtend.EntityManagerHelper;
import jdk.jfr.internal.Logger;
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

    @Getter
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
        EntityManagerHelper.persist(this); //TODO: Esto genera que se dupliquen las provincias en distintas líneas
    }

    //Devuelve una instancia de esta clase, de la base de datos si ya existe en ella, y nueva en caso contrario
    public static Provincias obtenerProvincia(Provincia provincia) {
        Provincias provinciaExistente;
        try {
            provinciaExistente = (Provincias) EntityManagerHelper.createQuery("from Provincias where provincia = '" + provincia.toEnumString() + "'");
            System.out.println("Encontrada provincia " + provincia.toString());

        } catch (NoResultException e)  {
            System.out.println("Creando provincia " + provincia.toString());
            return new Provincias(provincia);
        }
            return provinciaExistente;
    }

    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = municipiosODepartamentos.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }
}
