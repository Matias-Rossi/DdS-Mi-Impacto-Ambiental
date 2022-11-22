package impacto_ambiental.models.entities.reportes;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioProvincias;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(Provincia provincia){
        return this.generarReporteComposicion(provincia.getHcHistoricos());
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincia> provincias){
        RepositorioProvincias repositorio = new RepositorioProvincias();
        return this.generarReporteComposicion(provincias.stream().map(prov->prov.getHcHistoricos()).flatMap(Collection::stream).collect(Collectors.toList()));
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

    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(Provincia provincia){
        ReporteHistorico reporte = new ReporteHistorico();
        provincia.getHcHistoricos().forEach(e->reporte.agregarHc(e));
        return reporte;
    }

    public ReporteHistorico EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        ReporteHistorico reporte = new ReporteHistorico();
        organizacion.getHChistoricos().forEach(e->reporte.agregarHc(e));
        return reporte;
    }

    public static double round(double value) {
        if (1 < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


}
