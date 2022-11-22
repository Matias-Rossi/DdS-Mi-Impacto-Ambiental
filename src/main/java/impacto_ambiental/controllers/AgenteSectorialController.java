package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.OrgHc;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import impacto_ambiental.models.entities.reportes.ReporteComposicion;
import impacto_ambiental.models.entities.reportes.ReporteHistorico;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.*;
import org.apache.maven.model.Model;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AgenteSectorialController {



    public ModelAndView pantallaAgenteSectorial(Request request, Response response) {
        return new ModelAndView(null, "/agenteSectorial/homeAgente.hbs");
    }

    public ModelAndView hctotal(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);
        Double hcTotal = GeneradorDeReportes.round(sector.calcularHC());
        List<OrgHc> hcxorg = sector.getOrganizaciones().stream().map(org->new OrgHc(hcTotal,org)).collect(Collectors.toList());

        return new ModelAndView(new HashMap<String, Object>(){{
            put("hc", hcTotal);
            put("sector", sector.getSector());
            put("hcxorg",hcxorg);
        }}, "/agenteSectorial/agenteHCTotal.hbs");
    }

    public ModelAndView desplegarOrganizaciones(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);
        List<Organizacion> organizaciones = sector.getOrganizaciones();
        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", organizaciones);
        }}, "/agenteSectorial/organizacionesSeleccionar.hbs");
    }
    public ModelAndView botonesOrganizaciones(Request request, Response response) {
        String idOrganizacion = request.params("idOrganizacion");
        return new ModelAndView(new HashMap<String, Object>(){{
            put("id", idOrganizacion);
        }}, "/agenteSectorial/botonesOrganizaciones.hbs");
    }



    public ModelAndView composicionOrganizacion(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        int id = Integer.valueOf(request.params("idOrganizacion"));
        Organizacion organizacion = repositorioOrganizaciones.buscarPorId(id);

        String nombre = organizacion.getRazonSocial();



        ReporteComposicion reporte = GeneradorDeReportes.getInstance().composicionDeHCDeUnaOrganizacion(organizacion);
        reporte.calcularPorcentajes();


        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombre",nombre);
            put("reporte", reporte);
        }}, "/agenteSectorial/composicionOrganizacion.hbs");
    }

    public ModelAndView historicoOrganizacion(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        int id = Integer.valueOf(request.params("idOrganizacion"));
        Organizacion organizacion = repositorioOrganizaciones.buscarPorId(id);
        String nombre = organizacion.getRazonSocial();


        ReporteHistorico reporte = GeneradorDeReportes.getInstance().EvolucionDeHCTotalDeUnaOrganizacion(organizacion);
        reporte.ordenar();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombre",nombre);
            put("reporte", reporte);
        }}, "agenteSectorial/historicoOrganizacion.hbs");
    }


    public ModelAndView mostrarOrganizaciones(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);
        final List<Organizacion> organizaciones = sector.getOrganizaciones();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", sector.getOrganizaciones());
            put("sector", sector.getSector());
        }}, "/agenteSectorial/organizaciones.hbs");
    }

    public ModelAndView detalleOrganizacion(Request request, Response response) {
        final Integer idOrganizacion = Integer.valueOf(request.params("idClasificacion"));
        Organizacion org = new RepositorioOrganizaciones().buscarPorId(idOrganizacion);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("razonSocial", org.getRazonSocial());
            put("hcTotal", org.getHCTotal());
        }}, "/agenteSectorial/detalleOrganizacion.hbs");
    }

    public ModelAndView clasOrg(Request request, Response response) {
        RepositorioClasificacion repoClas= new RepositorioClasificacion();
        List<Clasificacion> clasificaciones= repoClas.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("clasificaciones", clasificaciones);
        }}, "/agenteSectorial/agenteHCClas.hbs");
    }

    public ModelAndView hcClasificacion(Request request, Response response) {
        String idClas= request.params("idClasificacion");
        final SectorTerritorial sector = getSector(request);

        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();

        Clasificacion clasificacion = repositorioClasificacion.buscar(Integer.valueOf(idClas));


        Double hc = sector.hcPorClas(clasificacion);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("hcClasificacion", hc);
            put("clasificacion",clasificacion);
        }}, "/agenteSectorial/Clasificacion/agenteClasificacion.hbs");
    }




    public ModelAndView botones(Request request, Response response) {
        return new ModelAndView(null, ".hbs");
    }

    public ModelAndView hcComposicion(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);

        String nombre = sector.nombreSector();

        ReporteComposicion reporte = sector.composicionDeHc();
        reporte.calcularPorcentajes();
        System.out.println("aaaaaaaaaa  tipoDeActividadSERVICIO_CONTRATADO");
        System.out.println(reporte.porcentajetipoDeActividadCOMBUSTION_MOVIL);


        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombre",nombre);
            put("reporte", reporte);
        }}, "/agenteSectorial/agenteComposicionHC.hbs");
    }

    public ModelAndView hcHistorico(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);

        ReporteHistorico reporte = sector.historicoHc();
        reporte.ordenar();
        String nombre = sector.nombreSector();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("nombre",nombre);
            put("reporte", reporte);
        }}, "/agenteSectorial/agenteHCHistorico.hbs");
    }

    public ModelAndView selectProvincias(Request request, Response response) {
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

        List<Provincia> provincias = repositorioProvincias.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("provincias", provincias);
        }}, "/agenteSectorial/seleccionarProvincias.hbs");
    }



    public Response solicitarProvincias(Request request, Response response) {

        response.redirect("/agenteSectorial/HCNacional/ComposicionHC");
        return response;
    }
    public ModelAndView hcNacional(Request request, Response response) {
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        List<Provincia> provincias = new ArrayList<>();

        String queryParam = request.queryParams("id");
        List<String> ids = List.of(queryParam.split(","));

        ids.forEach(id -> {
            provincias.add(repositorioProvincias.buscarPorId(Integer.parseInt(id)));
        });

        ReporteComposicion reporte = GeneradorDeReportes.getInstance().composicionDeaHCTotalANivelPais(provincias);
        reporte.calcularPorcentajes();


        return new ModelAndView(new HashMap<String, Object>() {{
            put("reporte",reporte);
        }}, "/agenteSectorial/composicionPais.hbs");
    }




        private SectorTerritorial getSector(Request request) {
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        Usuario usuario = repositorioUsuarios.buscarPorId(request.session().attribute("id"));
        SectorTerritorial sector = usuario.getSectorTerritorial();
        return sector;
    }




}
