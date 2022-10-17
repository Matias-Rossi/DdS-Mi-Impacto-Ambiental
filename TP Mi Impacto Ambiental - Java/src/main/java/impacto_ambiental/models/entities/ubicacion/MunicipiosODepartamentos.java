package impacto_ambiental.models.entities.ubicacion;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Importador;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Tipo;
import impacto_ambiental.models.entities.reportes.HChistorico;
import lombok.Getter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Entity
@Table(name = "municipiosODepartamentos")
public class MunicipiosODepartamentos extends SectorTerritorial {

    @Getter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "municipioODepartamento")
    List<Organizacion> organizaciones = new ArrayList<Organizacion>();

    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id")
    Provincia provincia;
    @Getter
    @Column(name = "municipioOLocalidad")
    private String municipioOLocalidad;

    public MunicipiosODepartamentos() {
        super("");
    }

    public Organizacion crearOrganizacion(Importador moduloImportador,String razonSocial,Tipo tipo, Clasificacion clasificacion,String loc,String cp,String cal,int num){
        Ubicacion ubicacion=new Ubicacion(this,loc,cp,cal,num);
        Organizacion org = new Organizacion(moduloImportador,ubicacion,razonSocial,tipo,clasificacion);
        organizaciones.add(org);
        return org;
    }

    public MunicipiosODepartamentos(Provincia provincia, String municipioOLocalidad){
        super(municipioOLocalidad);
        this.provincia=provincia;
        this.municipioOLocalidad=municipioOLocalidad;
    }
    public void calcularHC(){
        organizaciones.stream().forEach(e->e.calcularHC());
    }

    public NombreProvincia getProvincia() {
        return provincia.getNombreProvincia();
    }

    public List<HChistorico> getHcHistoricos(){
        return organizaciones.stream().map(e->e.getHChistoricos()).flatMap(e->e.stream()).collect(Collectors.toList());
    }

}
