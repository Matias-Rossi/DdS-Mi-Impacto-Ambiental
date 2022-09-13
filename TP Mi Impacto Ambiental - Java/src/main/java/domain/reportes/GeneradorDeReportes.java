package domain.reportes;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincias;

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
        return getReportesDeMunicipio(municipio).stream().mapToDouble(e->((Reportes) e).getHuellaDeCarbono()).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        return getReportesDeOrganizacionConClasificacion(clasificacion).stream().mapToDouble(e->((Reportes) e).getHuellaDeCarbono()).sum();
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        return getReportesDeOrganizacion(organizacion).stream().mapToDouble(e->((Reportes) e).getHuellaDeCarbono()).sum();
    }
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
    ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeMunicipio(municipio).forEach(e->
                reporteComposicion.sumar(((Reportes) e).getTipoActividad().toString(),((Reportes) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincias> provincias){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeTodasLasProvinciasMenos(provincias).forEach(e->
                reporteComposicion.sumar(((Reportes) e).getTipoActividad().toString(),((Reportes) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        getReportesDeOrganizacion(organizacion).forEach(e->
                reporteComposicion.sumar(((Reportes) e).getTipoActividad().toString(),((Reportes) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        List<Object> reportes =getReportesDeMunicipio(municipio);
        Integer anioMayor =getMayorAnio(reportes);
        Integer anioMenor =menorAnio(reportes);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(reportes,anioMenor)!=null){minMes = Periodo.Enero;
        }else minMes=((Reportes)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes,anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reportes)getReporteMaxDeUnAnioYMesMayor(reportes,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(((Reportes) e).getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(((Reportes) e).getAnio(),((Reportes) e).getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(((Reportes) e).getHuellaDeCarbono(),((Reportes) e).getAnio(),((Reportes) e).getPeriodo());
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
        }else minMes=((Reportes)getMinReporteDeUnAnio(reportes,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(reportes,anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((Reportes)getReporteMaxDeUnAnioYMesMayor(reportes,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        reportes.forEach(e->{if(((Reportes) e).getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(((Reportes) e).getAnio(),((Reportes) e).getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(((Reportes) e).getHuellaDeCarbono(),((Reportes) e).getAnio(),((Reportes) e).getPeriodo());
                }}
        );
        return reporteHistorico;
    }

    public Object getMinReporteDeUnAnio(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reportes) e).getAnio()==anio).min((e1,e2)->Periodo.toInteger(((Reportes) e1).getPeriodo())-Periodo.toInteger(((Reportes) e2).getPeriodo())).get();
    }
    public Object getReporteMaxDeUnAnioYMesMayor(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reportes) e).getAnio()==anio).max((e1,e2)->Periodo.toInteger(((Reportes) e1).getPeriodo())-Periodo.toInteger(((Reportes) e2).getPeriodo())).get();
    }
    private Object getReporteAnualDeUnAnio(List<Object> reportes, int anio){
        return reportes.stream().filter(e->((Reportes) e).getAnio()==anio && ((Reportes) e).getPeriodo()==Periodo.Anual).findFirst().get();
    }
    private int menorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reportes) e).getAnio()).min().getAsInt();
    }
    private int getMayorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reportes) e).getAnio()).max().getAsInt();
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
    private List<Object> getReportesDeTodasLasProvinciasMenos(List<Provincias> provincia){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
       }

}
