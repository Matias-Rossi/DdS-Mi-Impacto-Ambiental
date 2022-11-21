package impacto_ambiental.models.entities.ubicacion;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import impacto_ambiental.models.entities.reportes.ReporteComposicion;
import impacto_ambiental.models.entities.reportes.ReporteHistorico;
import impacto_ambiental.models.entities.usuario.Usuario;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
    name = "tipo")
public abstract class SectorTerritorial extends EntidadPersistente {
    @Column
    String discriminador;

    @Getter
    @Column(name = "sector")
    String sector;
    @OneToMany(mappedBy = "sectorTerritorialSolicitado", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)

    private List<Usuario> solicitudes=new ArrayList<>();

    public SectorTerritorial(String sector) {
        this.sector = sector;
    }

    public SectorTerritorial() {

    }

    public List<Organizacion> getOrganizaciones() {
        return new ArrayList<Organizacion>();
    }

    public double calcularHC() {
        return 0;
    }

    public void solicitarUnirse(Usuario usuario){
        solicitudes.add(usuario);
    }

    public void respuestaSolicitud(Usuario usuario){
        solicitudes.remove(usuario);
        usuario.agregarSector(this);
    }

    public ReporteComposicion composicionDeHc(){
        return null;
    }

    public ReporteHistorico historicoHc(){
        return null;
    }

    public Double hcPorClas(Clasificacion clasificacion) {return null;}

    public String nombreSector(){return null;}

}
