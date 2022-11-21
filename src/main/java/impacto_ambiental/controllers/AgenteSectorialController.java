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
        

        return new ModelAndView(new HashMap<String, Object>(){{
        }}, "/agenteSectorial/seleccionarProvincias.hbs");
    }



    public Response solicitarProvincias(Request request, Response response) {

        response.redirect("/agenteSectorial/HCNacional/ComposicionHC");
        return response;
    }
    public ModelAndView hcNacional(Request request, Response response) {
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        List<Provincia> provincias = new ArrayList<>();


        if(request.queryParams("BUENOS_AIRES")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.BUENOS_AIRES));
        if(request.queryParams("CIUDAD_DE_BUENOS_AIRES")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CIUDAD_DE_BUENOS_AIRES));
        if(request.queryParams("CORDOBA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CORDOBA));
        if(request.queryParams("CATAMARCA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CATAMARCA));
        if(request.queryParams("CHACO")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CHACO));
        if(request.queryParams("CHUBUT")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CHUBUT));
        if(request.queryParams("CORRIENTES")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.CORRIENTES));
        if(request.queryParams("ENTRE_RIOS")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.ENTRE_RIOS));
        if(request.queryParams("FORMOSA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.FORMOSA));
        if(request.queryParams("JUJUY")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.JUJUY));
        if(request.queryParams("LA_PAMPA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.LA_PAMPA));
        if(request.queryParams("LA_RIOJA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.LA_RIOJA));
        if(request.queryParams("MENDOZA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.MENDOZA));
        if(request.queryParams("MISIONES")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.MISIONES));
        if(request.queryParams("NEUQUEN")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.NEUQUEN));
        if(request.queryParams("RIO_NEGRO")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.RIO_NEGRO));
        if(request.queryParams("SALTA")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SALTA));
        if(request.queryParams("SAN_JUAN")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SAN_JUAN));
        if(request.queryParams("SAN_LUIS")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SAN_LUIS));
        if(request.queryParams("SANTA_CRUZ")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SANTA_CRUZ));
        if(request.queryParams("SANTA_FE")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SANTA_FE));
        if(request.queryParams("SANTIAGO_DEL_ESTERO")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.SANTIAGO_DEL_ESTERO));
        if(request.queryParams("TIERRA_DEL_FUEGO")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.TIERRA_DEL_FUEGO));
        if(request.queryParams("TUCUMAN")!=null) provincias.add(repositorioProvincias.getProvincia(NombreProvincia.TUCUMAN));

        ReporteComposicion reporte = GeneradorDeReportes.getInstance().composicionDeaHCTotalANivelPais(provincias);


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
