package domain.reportes;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincias;

import java.util.List;

public class GeneradorDeReportes {
    public double hCTotalPorSectorTerriorial(MunicipiosODepartamentos municipio){
        return municipio.getOrganizaciones().stream().mapToDouble(organizacion -> this.hcTotalPorOrganizacion(organizacion)).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        return (double) EntityManagerHelper.createQuery("SELECT SUM(r.huellaDeCarbono) FROM Reportes r WHERE r.organizacion.clasificacion = :" + clasificacion);
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        return (double) EntityManagerHelper.createQuery("SELECT SUM(r.huellaDeCarbono) FROM Reportes r WHERE r.organizacion = :"+organizacion);
    }
    public String composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        return "";
    }
    public String composicionDeaHCTotalANivelPais(List<Provincias> municipios){
        return "";
    }
    public String composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        return "";
    }
    public String EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        return "";
    }
    public String EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        return "";
    }
}
