package proservices.models.entities.reportes;

import proservices.models.entities.perfil.Clasificacion;
import proservices.models.entities.perfil.Organizacion;
import proservices.models.entities.ubicacion.MunicipiosODepartamentos;
import proservices.models.entities.ubicacion.Provincia;
import proservices.models.repositorios.RepositorioOrganizaciones;
import proservices.models.repositorios.RepositorioProvincias;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class GeneradorDeReportes {

    private static GeneradorDeReportes instance = null;

    private GeneradorDeReportes() {
    }

    public static GeneradorDeReportes getInstance() {
        if (instance == null) {
            instance = new GeneradorDeReportes();
        }
        return instance;
    }
    public double hCTotalPorSectorTerriorial(MunicipiosODepartamentos municipio){
        return municipio.getHcHistoricos().stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        RepositorioOrganizaciones repositorio = new RepositorioOrganizaciones();
        return repositorio.getOrganizacionesPorClasificacion(clasificacion).stream().map(e->e.getHChistoricos()).flatMap(Collection::stream).mapToDouble(e->e.getHuellaDeCarbono()).sum();
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        return organizacion.getHChistoricos().stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        return this.generarReporteComposicion(municipio.getHcHistoricos());
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincia> provincias){
        RepositorioProvincias repositorio = new RepositorioProvincias();
        return this.generarReporteComposicion(
                repositorio.getProvinciasMenos(provincias).stream().map(
                        e->e.getHChistoricos()).flatMap(Collection::stream).collect(Collectors.toList()
                )
        );
    }

    private ReporteComposicion generarReporteComposicion(List<HChistorico> historicos){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        historicos.forEach(e->reporteComposicion.sumar(e.getTipoActividad().toString(),e.getHuellaDeCarbono()));
        return reporteComposicion;
    }

    public ReporteComposicion composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        return this.generarReporteComposicion(organizacion.getHChistoricos());
    }
    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        ReporteHistorico reporte = new ReporteHistorico();
        municipio.getHcHistoricos().forEach(e->reporte.agregarHc(e));
        return reporte;
        }
        
    public ReporteHistorico EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        ReporteHistorico reporte = new ReporteHistorico();
        organizacion.getHChistoricos().forEach(e->reporte.agregarHc(e));
        return reporte;
    }


}
