package impacto_ambiental.models.entities.ubicacion;

import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.repositorios.RepositorioMunicipiosODepartamentos;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "provincias")
public class Provincia extends SectorTerritorial {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private NombreProvincia nombreProvincia;

    @Getter
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "provincia")
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


    public void calcularHC(Integer anio, Integer mes){
        municipiosODepartamentos.stream().forEach(e->e.calcularHC());
    }

    public List<HChistorico> getHChistoricos(){
        return municipiosODepartamentos.stream().map(e->e.getHcHistoricos()).flatMap(e->e.stream()).collect(Collectors.toList());
    }
}
