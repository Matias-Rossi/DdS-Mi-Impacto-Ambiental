package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import impacto_ambiental.models.entities.reportes.ReporteComposicion;
import impacto_ambiental.models.entities.reportes.ReporteHistorico;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ReportesOrganizacionController {
  public ModelAndView pantallaReportesOrganizacion(Request request, Response response) {
    return new ModelAndView(null, "usuarioOrganizacion/Reportes/botonesReportes.hbs");
  }

  public ModelAndView hcComposicion(Request request, Response response) {
    RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
    int id = request.session().attribute("id");
    Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(id);
    String nombre = organizacion.getRazonSocial();



    ReporteComposicion reporte = GeneradorDeReportes.getInstance().composicionDeHCDeUnaOrganizacion(organizacion);
    reporte.calcularPorcentajes();


    return new ModelAndView(new HashMap<String, Object>(){{
      put("nombre",nombre);
      put("reporte", reporte);
    }}, "usuarioOrganizacion/Reportes/organizacionComposicionHC.hbs");
  }

  public ModelAndView hcHistorico(Request request, Response response) {
    RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
    int id = request.session().attribute("id");
    Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(id);
    String nombre = organizacion.getRazonSocial();


    ReporteHistorico reporte = GeneradorDeReportes.getInstance().EvolucionDeHCTotalDeUnaOrganizacion(organizacion);
    reporte.ordenar();

    return new ModelAndView(new HashMap<String, Object>(){{
      put("nombre",nombre);
      put("reporte", reporte);
    }}, "usuarioOrganizacion/Reportes/organizacionHCHistorico.hbs");
  }

}
