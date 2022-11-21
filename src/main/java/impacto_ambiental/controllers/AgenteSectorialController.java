package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import impacto_ambiental.models.entities.reportes.ReporteComposicion;
import impacto_ambiental.models.entities.reportes.ReporteHistorico;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.*;
import org.apache.maven.model.Model;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AgenteSectorialController {



    public ModelAndView pantallaAgenteSectorial(Request request, Response response) {
        return new ModelAndView(null, "/agenteSectorial/homeAgente.hbs");
    }

    public ModelAndView hctotal(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);
        return new ModelAndView(new HashMap<String, Object>(){{
            put("hc", sector.calcularHC());
            put("sector", sector.getSector());
        }}, "/agenteSectorial/agenteHCTotal.hbs");
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

        ReporteComposicion reporte = sector.composicionDeHc();
        reporte.calcularPorcentajes();
        System.out.println("aaaaaaaaaa  tipoDeActividadSERVICIO_CONTRATADO");
        System.out.println(reporte.porcentajetipoDeActividadCOMBUSTION_MOVIL);


        return new ModelAndView(new HashMap<String, Object>(){{
            put("reporte", reporte);
        }}, "/agenteSectorial/agenteComposicionHc.hbs");
    }

    public ModelAndView hcHistorico(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);

        ReporteHistorico reporte = sector.historicoHc();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("reporte", reporte);
        }}, "/agenteSectorial/agenteHcHistorico.hbs");
    }

    private SectorTerritorial getSector(Request request) {
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        Usuario usuario = repositorioUsuarios.buscarPorId(request.session().attribute("id"));
        SectorTerritorial sector = usuario.getSectorTerritorial();
        return sector;
    }




}
