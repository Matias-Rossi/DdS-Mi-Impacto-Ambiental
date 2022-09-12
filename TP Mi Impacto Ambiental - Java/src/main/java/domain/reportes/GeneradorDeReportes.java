package domain.reportes;

import domain.perfil.Organizacion;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincias;

import java.util.List;

public class GeneradorDeReportes {
    public double hCTotalPorSectorTerriorial(MunicipiosODepartamentos municipio){
        List<Organizacion> organizaciones = municipio.getOrganizaciones();
        double sumaHC = organizaciones.stream().mapToDouble(org -> org.getHCTotal()).sum();
        return sumaHC;
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
