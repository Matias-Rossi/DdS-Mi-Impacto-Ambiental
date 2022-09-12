package domain.reportes;

import domain.perfil.Organizacion;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincias;

import java.util.List;

public class GeneradorDeReportes {
    public double hCTotalPorSectorTerriorial(MunicipiosODepartamentos municipio){
        return 0;
    }

    public double hCTotalPorTipoDeOrganizacion(Organizacion organizacion){
        return 0;
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
