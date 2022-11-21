package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.SolicitudEstado;
import impacto_ambiental.models.repositorios.RepositorioAreas;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class AreasOrganizacionController {
  RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
  RepositorioAreas repositorioAreas = new RepositorioAreas();

  public ModelAndView mostrarPropias(Request request, Response response) {
    Organizacion unaOrganizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<Area> areas = unaOrganizacion.getAreas();

    return new ModelAndView(new HashMap<String, Object>(){{
      put("areas", areas);
      put("nombreOrganizacion", unaOrganizacion.getRazonSocial());
    }}, "usuarioOrganizacion/areasOrganizacion.hbs");
  }
  
  public Response guardar(Request request, Response response) {
    String nombre = request.queryParams("name");
    Organizacion unaOrganizacion = obtenerOrganizacionSegunIDUsuario(request);
    unaOrganizacion.getAreas();
    //Area areaNueva = unaOrganizacion.darAltaArea(nombre);
    Area areaNueva = new Area(nombre, unaOrganizacion);

    repositorioAreas.agregar(areaNueva);

    System.out.println("antes de agregar Areas " +areaNueva.nombre);
    //repositorioOrganizaciones.actualizar(unaOrganizacion);
    System.out.println("Area agregada");

    response.redirect("/areas");
    return response;
  }

  public Response borrar(Request request, Response response) {
    RepositorioAreas repositorioAreas = new RepositorioAreas();
    String idArea = request.params("idArea");

    Area area =repositorioAreas.buscarPorId(Integer.valueOf(idArea));
    area.miembrosActuales().forEach(a->a.setEstado(SolicitudEstado.DESVINCULADO));
    area.setOrganizacion(null);
    repositorioAreas.actualizar(area);
    response.redirect("/areas");
    return response;
  }


  private Organizacion obtenerOrganizacionSegunIDUsuario(Request request) {
    return repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
  }

}
