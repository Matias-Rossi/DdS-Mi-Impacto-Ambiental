package impacto_ambiental.models.entities.ubicacion;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import impacto_ambiental.models.entities.reportes.HChistorico;
import impacto_ambiental.models.entities.reportes.ReporteComposicion;
import impacto_ambiental.models.entities.reportes.ReporteHistorico;
import impacto_ambiental.models.repositorios.RepositorioMunicipiosODepartamentos;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public double calcularHC(){

        return municipiosODepartamentos.stream().mapToDouble(e->e.calcularHC()).sum();

    }

    public List<HChistorico> getHChistoricos(){
        return municipiosODepartamentos.stream().map(e->e.getHcHistoricos()).flatMap(e->e.stream()).collect(Collectors.toList());
    }

    @Override
    public List<Organizacion> getOrganizaciones() {
        List<Organizacion> organizaciones = new ArrayList<>();
        this.municipiosODepartamentos.forEach(_mun -> organizaciones.addAll(_mun.organizaciones));
        return organizaciones;
    }
    public List<HChistorico> getHcHistoricos(){
        return this.municipiosODepartamentos.stream().map(mun->mun.getHcHistoricos()).flatMap(Collection::stream).collect(Collectors.toList());
    }
    @Override
    public ReporteComposicion composicionDeHc(){
        return GeneradorDeReportes.getInstance().composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(this);
    }
    @Override
    public ReporteHistorico historicoHc(){
        return GeneradorDeReportes.getInstance().EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(this);
    }
    @Override
    public Double hcPorClas(Clasificacion clasificacion){
        return municipiosODepartamentos.stream().mapToDouble(e->
                e.hcPorClas(clasificacion)
        ).sum();
    }
    @Override
    public String nombreSector(){
        return this.nombreProvincia.toString();
    }
}
