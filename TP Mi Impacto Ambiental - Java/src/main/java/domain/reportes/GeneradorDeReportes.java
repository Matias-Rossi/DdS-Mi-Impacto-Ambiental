package domain.reportes;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
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
        return getReportesDeMunicipio(municipio).stream().mapToDouble(e->((Reporte) e).getHuellaDeCarbono()).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        return getReportesDeOrganizacionConClasificacion(clasificacion).stream().mapToDouble(e->((Reporte) e).getHuellaDeCarbono()).sum();
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        return getReportesDeOrganizacion(organizacion).stream().mapToDouble(e->((Reporte) e).getHuellaDeCarbono()).sum();
    }
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
    ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeMunicipio(municipio).forEach(e->
                reporteComposicion.sumar(((Reporte) e).getTipoActividad().toString(),((Reporte) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincia> provincias){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeTodasLasProvinciasMenos(provincias).forEach(e->
                reporteComposicion.sumar(((Reporte) e).getTipoActividad().toString(),((Reporte) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeOrganizacion(organizacion).forEach(e->
                reporteComposicion.sumar(((Reporte) e).getTipoActividad().toString(),((Reporte) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        List<Object> reportes =getReportesDeMunicipio(municipio);
        Integer anioMayor =getMayorAnio(reportes);
        Integer anioMenor =menorAnio(reportes);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(reportes,anioMenor)!=null){minMes = Periodo.Enero;
        }else minMes=((Reporte)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes,anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reporte)getReporteMaxDeUnAnioYMesMayor(reportes,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(((Reporte) e).getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(((Reporte) e).getAnio(),((Reporte) e).getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(((Reporte) e).getHuellaDeCarbono(),((Reporte) e).getAnio(),((Reporte) e).getPeriodo());
                }}
            );
        return reporteHistorico;
        }




    public ReporteHistorico EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        List<Object> reportes =getReportesDeOrganizacion(organizacion);
        Integer anioMayor =getMayorAnio(reportes);
        Integer anioMenor =menorAnio(reportes);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(reportes,anioMenor)!=null){minMes = Periodo.Enero;
        }else minMes=((Reporte)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes,anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reporte)getReporteMaxDeUnAnioYMesMayor(reportes,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(((Reporte) e).getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(((Reporte) e).getAnio(),((Reporte) e).getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(((Reporte) e).getHuellaDeCarbono(),((Reporte) e).getAnio(),((Reporte) e).getPeriodo());
                }}
        );
        return reporteHistorico;
    }

    public Object getMinReporteDeUnAnio(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reporte) e).getAnio()==anio).min((e1, e2)->Periodo.toInteger(((Reporte) e1).getPeriodo())-Periodo.toInteger(((Reporte) e2).getPeriodo())).get();
    }
    public Object getReporteMaxDeUnAnioYMesMayor(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reporte) e).getAnio()==anio).max((e1, e2)->Periodo.toInteger(((Reporte) e1).getPeriodo())-Periodo.toInteger(((Reporte) e2).getPeriodo())).get();
    }
    private Object getReporteAnualDeUnAnio(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reporte) e).getAnio()==anio && ((Reporte) e).getPeriodo()==Periodo.Anual).findFirst().get();
    }
    private int menorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reporte) e).getAnio()).min().getAsInt();
    }
    private int getMayorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reporte) e).getAnio()).max().getAsInt();
    }

    private List<Object> getReportesDeOrganizacion(Organizacion organizacion){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion = :"+organizacion);
    }
    private List<Object> getReportesDeOrganizacionConClasificacion(Clasificacion clasificacion){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.clasificacion = :"+clasificacion);
    }
    private List<Object> getReportesDeMunicipio(MunicipiosODepartamentos municipio){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento = :"+municipio);
    }
    private List<Object> getReportesDeTodasLasProvinciasMenos(List<Provincia> provincia){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
       }

}
