package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.OrgPorcentaje;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.repositorios.RepositorioMiembros;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CalcularHcController {

    public ModelAndView mostrarHCMiembro(Request request, Response response) {

        RepositorioSolicitudes repositorioSolicitudes = new RepositorioSolicitudes();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

        Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));

        List<Solicitud> solicitudes = repositorioSolicitudes.buscarSolicitudesAceptadasPorIDMiembro(unMiembro.getId());
        List<Organizacion> organizacions = solicitudes.stream().map(solicitud->solicitud.getArea().getOrganizacion()).collect(Collectors.toList());
        List<OrgPorcentaje> porcentajes = organizacions.stream().map(org->new OrgPorcentaje(org,unMiembro.calcularHCPorcentual(org))).collect(Collectors.toList());
        Double hc = unMiembro.calcularHCTotal();


        return new ModelAndView(new HashMap<String, Object>() {{
            put("hc",hc);
            put("porcentajes", porcentajes);
        }}, "calcularHCUser.hbs"); //TODO
    }


    public Response calcularHcMiembro(Request request, Response response) {
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));
        unMiembro.calcularHcTodo();
        repositorioMiembros.actualizar(unMiembro);
        response.redirect("/calcularhc");
        return response;
    }

    public Response calcularHcOrganizacion(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
        organizacion.getHCTotal();
        //No está desnormalizado, pa que el post? no sé
        response.redirect("/organizacion/calcularHC");
        return response;
    }

    public ModelAndView mostrarHcOrganizacion(Request request, Response response) {
        Organizacion organizacion = new RepositorioOrganizaciones().obtenerOrganizacionSegunRequest(request);
        double hc = organizacion.getHCTotal();

        return new ModelAndView(new HashMap<String, Object>() {{
            put("hc",hc);
        }}, "usuarioOrganizacion/calcularHCOrganizacion.hbs"); //TODO
    }


}
