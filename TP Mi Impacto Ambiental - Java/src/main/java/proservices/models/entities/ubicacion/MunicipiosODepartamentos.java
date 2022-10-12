package proservices.models.entities.ubicacion;

import proservices.models.entities.perfil.Clasificacion;
import proservices.models.entities.perfil.Importador;
import proservices.models.entities.perfil.Organizacion;
import proservices.models.entities.perfil.Tipo;
import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.reportes.HChistorico;
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
    Provincia provincia;
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

    public MunicipiosODepartamentos(Provincia provincia, String municipioOLocalidad){
        this.provincia=provincia;
        this.municipioOLocalidad=municipioOLocalidad;
    }
    public double calcularHC(Integer anio, Integer mes){
        List<Double> mapped = organizaciones.stream().map(e->e.calcularHC(anio,mes)).collect(Collectors.toList());
        return mapped.stream().reduce(0.0, (a, b) ->a+b);
    }

    public NombreProvincia getProvincia() {
        return provincia.getNombreProvincia();
    }

    public List<HChistorico> getHcHistoricos(){
        return organizaciones.stream().map(e->e.getHChistoricos()).flatMap(e->e.stream()).collect(Collectors.toList());
    }

}
