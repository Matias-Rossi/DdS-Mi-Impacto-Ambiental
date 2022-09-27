package domain.reportes;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.repositorios.RepositorioReportes;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincia;

import java.util.List;

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
        RepositorioReportes repositorio = new RepositorioReportes();
        return repositorio.getReportesDeMunicipio(municipio).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        RepositorioReportes repositorio = new RepositorioReportes();
        return repositorio.getReportesDeOrganizacionConClasificacion(clasificacion).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        RepositorioReportes repositorio = new RepositorioReportes();
        return repositorio.getReportesDeOrganizacion(organizacion).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
    RepositorioReportes repositorio = new RepositorioReportes();
    ReporteComposicion reporteComposicion = new ReporteComposicion();
        repositorio.getReportesDeMunicipio(municipio).forEach(e->
                reporteComposicion.sumar(e.getTipoActividad().toString(), e.getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincia> provincias){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        RepositorioReportes repositorioReportes = new RepositorioReportes();
        //TODO Usar repositorio
        getReportesDeTodasLasProvinciasMenos(provincias).forEach(e->
                reporteComposicion.sumar(((Reporte) e).getTipoActividad().toString(),((Reporte) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        RepositorioReportes repositorio = new RepositorioReportes();
        repositorio.getReportesDeOrganizacion(organizacion).forEach(e->
                reporteComposicion.sumar(e.getTipoActividad().toString(), e.getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        RepositorioReportes repositorio = new RepositorioReportes();
        List<Reporte> reportes = repositorio.getReportesDeMunicipio(municipio);
        Integer anioMayor = getMayorAnio(reportes);
        Integer anioMenor = menorAnio(reportes);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(reportes, anioMenor) != null){minMes = Periodo.Enero;
        }else minMes=((Reporte)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes, anioMayor) != null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reporte)getReporteMaxDeUnAnioYMesMayor(reportes, anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(e.getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(e.getAnio(), e.getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(e.getHuellaDeCarbono(), e.getAnio(), e.getPeriodo());
                }}
            );
        return reporteHistorico;
        }




    public ReporteHistorico EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        RepositorioReportes repositorio = new RepositorioReportes();
        List<Reporte> reportes = repositorio.getReportesDeOrganizacion(organizacion);
        Integer anioMayor =getMayorAnio(reportes);
        Integer anioMenor =menorAnio(reportes);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(reportes, anioMenor)!=null){minMes = Periodo.Enero;
        }else minMes=((Reporte)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes, anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reporte)getReporteMaxDeUnAnioYMesMayor(reportes,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(e.getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(e.getAnio(), e.getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(e.getHuellaDeCarbono(), e.getAnio(), e.getPeriodo());
                }}
        );
        return reporteHistorico;
    }

    public Object getMinReporteDeUnAnio(List<Reporte> reportes, int anio){
        return reportes.stream().filter(e->((Reporte) e).getAnio()==anio).min((e1, e2)->Periodo.toInteger(((Reporte) e1).getPeriodo())-Periodo.toInteger(((Reporte) e2).getPeriodo())).get();
    }
    public Object getReporteMaxDeUnAnioYMesMayor(List<Reporte> reportes, int anio){
        return reportes.stream().filter(e->((Reporte) e).getAnio()==anio).max((e1, e2)->Periodo.toInteger(((Reporte) e1).getPeriodo())-Periodo.toInteger(((Reporte) e2).getPeriodo())).get();
    }
    private Reporte getReporteAnualDeUnAnio(List<Reporte> reportes, int anio){
        return (Reporte) reportes.stream().filter(e->((Reporte) e).getAnio()==anio && ((Reporte) e).getPeriodo()==Periodo.Anual).findFirst().get();
    }
    private int menorAnio(List<Reporte> reportes){
        return reportes.stream().mapToInt(e-> e.getAnio()).min().getAsInt();
    }
    private int getMayorAnio(List<Reporte> reportes){
        return reportes.stream().mapToInt(e-> e.getAnio()).max().getAsInt();
    }

    private List<Object> getReportesDeTodasLasProvinciasMenos(List<Provincia> provincia){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
    }

}
