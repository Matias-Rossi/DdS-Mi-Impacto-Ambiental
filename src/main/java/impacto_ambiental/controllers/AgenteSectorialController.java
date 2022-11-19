package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioMunicipiosODepartamentos;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioSectoresTerritoriales;
import impacto_ambiental.models.repositorios.RepositorioUsuarios;
import org.apache.maven.model.Model;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AgenteSectorialController {



    public ModelAndView mostrarSector(Request request, Response response) {
        final SectorTerritorial sector = getSector(request);


        return new ModelAndView(new HashMap<String, Object>(){{
            put("hc", sector.calcularHC());
            put("sector", sector.getSector());
        }}, "/agenteSectorial/homeAgente.hbs");
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
        final Integer idOrganizacion = Integer.valueOf(request.params("id"));
        Organizacion org = new RepositorioOrganizaciones().buscarPorId(idOrganizacion);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("razonSocial", org.getRazonSocial());
            put("hcTotal", org.getHCTotal());
        }}, "/agenteSectorial/detalleOrganizacion.hbs");
    }

    private SectorTerritorial getSector(Request request) {
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        Usuario usuario = repositorioUsuarios.buscarPorId(request.session().attribute("id"));
        SectorTerritorial sector = usuario.getSectorTerritorial();
        return sector;
    }




}
