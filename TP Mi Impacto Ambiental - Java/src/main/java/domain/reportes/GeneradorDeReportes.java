package domain.reportes;

import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.persistenceExtend.EntityManagerHelper;
import domain.persistenceExtend.repositorios.RepositorioHCHistoricos;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.Provincia;

import java.util.ArrayList;
import java.util.List;

public final class GeneradorDeReportes {
/*
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
        //RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        //repositorio.getReportesDeMunicipio(municipio).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
        return municipio.getHcHistoricos().stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }

    public double hCTotalPorTipoDeOrganizacion(Clasificacion clasificacion){
        RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        repositorio.getReportesDeOrganizacionConClasificacion(clasificacion).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }
    private double hcTotalPorOrganizacion(Organizacion organizacion){
        //RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        //repositorio.getReportesDeOrganizacion(organizacion).stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
        return organizacion.getHChistoricos().stream().mapToDouble(e-> e.getHuellaDeCarbono()).sum();
    }
    public ReporteComposicion composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
    RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
    ReporteComposicion reporteComposicion = new ReporteComposicion();
        repositorio.getReportesDeMunicipio(municipio).forEach(e->
                reporteComposicion.sumar(e.getTipoActividad().toString(), e.getHuellaDeCarbono()));
        
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeaHCTotalANivelPais(List<Provincia> provincias){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        RepositorioHCHistoricos repositorioHCHistoricos = new RepositorioHCHistoricos();
        //TODO Usar repositorio
        getReportesDeTodasLasProvinciasMenos(provincias).forEach(e->
                reporteComposicion.sumar(((HChistorico) e).getTipoActividad().toString(),((HChistorico) e).getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteComposicion composicionDeHCDeUnaOrganizacion(Organizacion organizacion){
        ReporteComposicion reporteComposicion = new ReporteComposicion();
        RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        repositorio.getReportesDeOrganizacion(organizacion).forEach(e->
                reporteComposicion.sumar(e.getTipoActividad().toString(), e.getHuellaDeCarbono()));
        return reporteComposicion;
    }
    public ReporteHistorico EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(MunicipiosODepartamentos municipio){
        RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        List<HChistorico> HChistoricos = repositorio.getReportesDeMunicipio(municipio);
        Integer anioMayor = getMayorAnio(HChistoricos);
        Integer anioMenor = menorAnio(HChistoricos);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(HChistoricos, anioMenor) != null){minMes = Periodo.Enero;
        }else minMes=((HChistorico)getMinReporteDeUnAnio(HChistoricos,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(HChistoricos, anioMayor) != null){maxMes = Periodo.Diciembre;
        }else maxMes=((HChistorico)getReporteMaxDeUnAnioYMesMayor(HChistoricos, anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        HChistoricos.forEach(e->{if(e.getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(e.getAnio(), e.getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(e.getHuellaDeCarbono(), e.getAnio(), e.getPeriodo());
                }}
            );
        return reporteHistorico;
        }




    public ReporteHistorico EvolucionDeHCTotalDeUnaOrganizacion(Organizacion organizacion){
        RepositorioHCHistoricos repositorio = new RepositorioHCHistoricos();
        List<HChistorico> HChistoricos = repositorio.getReportesDeOrganizacion(organizacion);
        Integer anioMayor =getMayorAnio(HChistoricos);
        Integer anioMenor =menorAnio(HChistoricos);
        Periodo minMes;
        Periodo maxMes;
        if(getReporteAnualDeUnAnio(HChistoricos, anioMenor)!=null){minMes = Periodo.Enero;
        }else minMes=((HChistorico)getMinReporteDeUnAnio(HChistoricos,anioMenor)).getPeriodo();
        if(getReporteAnualDeUnAnio(HChistoricos, anioMayor)!=null){maxMes = Periodo.Diciembre;
        }else maxMes=((HChistorico)getReporteMaxDeUnAnioYMesMayor(HChistoricos,anioMenor)).getPeriodo();
        ReporteHistorico reporteHistorico = new ReporteHistorico(minMes,anioMenor,maxMes,anioMayor);

        HChistoricos.forEach(e->{if(e.getPeriodo()==Periodo.Anual){
                    reporteHistorico.agregarAnual(e.getAnio(), e.getHuellaDeCarbono());
                }else{
                    reporteHistorico.agregarFactorDeEmision(e.getHuellaDeCarbono(), e.getAnio(), e.getPeriodo());
                }}
        );
        return reporteHistorico;
    }

    public Object getMinReporteDeUnAnio(List<HChistorico> HChistoricos, int anio){
        return HChistoricos.stream().filter(e->((HChistorico) e).getAnio()==anio).min((e1, e2)->Periodo.toInteger(((HChistorico) e1).getPeriodo())-Periodo.toInteger(((HChistorico) e2).getPeriodo())).get();
    }
    public Object getReporteMaxDeUnAnioYMesMayor(List<HChistorico> HChistoricos, int anio){
        return HChistoricos.stream().filter(e->((HChistorico) e).getAnio()==anio).max((e1, e2)->Periodo.toInteger(((HChistorico) e1).getPeriodo())-Periodo.toInteger(((HChistorico) e2).getPeriodo())).get();
    }
    private HChistorico getReporteAnualDeUnAnio(List<HChistorico> HChistoricos, int anio){
        return (HChistorico) HChistoricos.stream().filter(e->((HChistorico) e).getAnio()==anio && ((HChistorico) e).getPeriodo()==Periodo.Anual).findFirst().get();
    }
<<<<<<< HEAD
    public int menorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reportes) e).getAnio()).min().getAsInt();
    }
    public int getMayorAnio(List<Object> reportes){
        return reportes.stream().mapToInt(e->((Reportes) e).getAnio()).max().getAsInt();
    }

    private List<Object> getReportesDeOrganizacion(Organizacion organizacion){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.razonSocial = '"+organizacion.getRazonSocial()+"'");
    }
    private List<Object> getReportesDeOrganizacionConClasificacion(Clasificacion clasificacion){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.clasificacion.nombre = '"+clasificacion.getNombre()+"'");
    }
    private List<Object> getReportesDeMunicipio(MunicipiosODepartamentos municipio){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.municipioOLocalidad = '"+municipio.getMunicipioOLocalidad()+"'");
    }
    public List<Object> getReportesDeTodasLasProvinciasMenos(List<Provincias> provincia){

        List<Object> reportes = new ArrayList<>();
        provincia.forEach(e->reportes.addAll(EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia.provincia = '"+e.getProvincia()+"'")));
        return reportes;
       }
       private Object getReporteDeProvincia(Provincias provincia){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia = '"+provincia.getProvincia()+"'");
       }
=======
    private int menorAnio(List<HChistorico> HChistoricos){
        return HChistoricos.stream().mapToInt(e-> e.getAnio()).min().getAsInt();
    }
    private int getMayorAnio(List<HChistorico> HChistoricos){
        return HChistoricos.stream().mapToInt(e-> e.getAnio()).max().getAsInt();
    }

    private List<Object> getReportesDeTodasLasProvinciasMenos(List<Provincia> provincia){
        return EntityManagerHelper.createQueryListResult("SELECT r FROM Reportes r WHERE r.organizacion.municipioODepartamento.provincia NOT IN :"+provincia);
    }
>>>>>>> dfe3fc8179e05180af67ed24bf315b1053bdd45d
*/

}
