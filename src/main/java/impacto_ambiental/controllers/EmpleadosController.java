package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.perfil.SolicitudEstado;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmpleadosController {
  RepositorioSolicitudes repositorioSolicitudes = new RepositorioSolicitudes();
  RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();

  public ModelAndView mostrarEmpleadosActuales(Request request, Response response) {
    Organizacion organizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<Solicitud> solicitudes = repositorioOrganizaciones.obtenerSolicitudes(organizacion);
    List<Solicitud> solicitudesEmpleados = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.ACEPTADA).toList();


    return new ModelAndView(new HashMap<String, Object>(){{
      put("empleados", solicitudesEmpleados);
    }}, "usuarioOrganizacion/empleadosActuales.hbs");
  }

  public ModelAndView mostrarEmpleadosPendientes(Request request, Response response) {
    Organizacion organizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<Solicitud> solicitudes = repositorioOrganizaciones.obtenerSolicitudes(organizacion);
    List<Solicitud> solicitudesPendientes = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.PENDIENTE).toList();
    return new ModelAndView(new HashMap<String, Object>(){{
      put("pendientes", solicitudesPendientes);
    }}, "usuarioOrganizacion/empleadosPendientes.hbs");
  }

  public Response desvincular(Request request, Response response) {
    String idSolicitud = request.params("idSolicitud");
    RepositorioSolicitudes solicitudes= new RepositorioSolicitudes();
    Solicitud sol = solicitudes.buscarPorId(Integer.valueOf(idSolicitud));
    sol.setEstado(SolicitudEstado.DESVINCULADO);

    solicitudes.actualizar(sol);
    response.redirect("/empleadosActuales");
    return response;
  }

  public Response aceptarEmpleado(Request request, Response response) {
    int idSolicitud = Integer.parseInt(request.queryParams("id"));
    Solicitud solicitud = repositorioSolicitudes.buscar(idSolicitud);
    solicitud.setEstado(SolicitudEstado.ACEPTADA);
    repositorioSolicitudes.actualizar(solicitud);

    response.redirect("/empleados/pendientes");
    return response;
  }

  public Response rechazarEmpleado(Request request, Response response) {
    int idSolicitud = Integer.parseInt(request.queryParams("id"));
    Solicitud solicitud = repositorioSolicitudes.buscar(idSolicitud);
    solicitud.setEstado(SolicitudEstado.RECHAZADA);
    repositorioSolicitudes.actualizar(solicitud);

    response.redirect("/empleados/pendientes");
    return response;
  }

  public ModelAndView elegirPantalla(Request request, Response response) {
    Organizacion organizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<Solicitud> solicitudes = repositorioOrganizaciones.obtenerSolicitudes(organizacion);
    int cantPendientes = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.PENDIENTE).toList().size();
    int cantEmpleados = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.ACEPTADA).toList().size();

    return new ModelAndView(new HashMap<String, Object>(){{
      put("cantPendientes", cantPendientes);
      put("cantEmpleados", cantEmpleados);
    }}, "usuarioOrganizacion/empleadosOrganizacion.hbs");//TODO agregar handlebar
  }

  private Organizacion obtenerOrganizacionSegunIDUsuario(Request request) {
    return repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
  }
}
