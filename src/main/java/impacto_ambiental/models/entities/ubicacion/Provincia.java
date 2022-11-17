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
@DiscriminatorValue("Provincia")
public class Provincia extends SectorTerritorial {

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre")
    private NombreProvincia nombreProvincia;

    @Getter
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "provincia")
    List<MunicipiosODepartamentos> municipiosODepartamentos = new ArrayList<>();

    public Provincia() {
        super("");
    }

    public MunicipiosODepartamentos crearMunicipio(String municipioNombre){
        MunicipiosODepartamentos muni = new MunicipiosODepartamentos(this,municipioNombre);
        this.municipiosODepartamentos.add(muni);
        return muni;
    }

    public Provincia(NombreProvincia nombreProvincia){
        super(nombreProvincia.toString());
        this.nombreProvincia = nombreProvincia;
    }


    public void calcularHC(Integer anio, Integer mes){
        municipiosODepartamentos.stream().forEach(e->e.calcularHC());
    }

    public List<HChistorico> getHChistoricos(){
        return municipiosODepartamentos.stream().map(e->e.getHcHistoricos()).flatMap(e->e.stream()).collect(Collectors.toList());
    }
}
