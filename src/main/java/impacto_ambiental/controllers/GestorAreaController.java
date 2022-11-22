package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.perfil.SolicitudEstado;
import impacto_ambiental.models.repositorios.RepositorioAreas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GestorAreaController {
  RepositorioAreas repositorioAreas = new RepositorioAreas();

  //Mostrar empleados y estado?
  public ModelAndView mostrar(Request request, Response response) {
    String idArea = request.params("idArea");
    Area area = repositorioAreas.buscarPorId(Integer.valueOf(idArea));

    String nombre = "";
    List<Solicitud> solicitudes = new ArrayList<Solicitud>();
    List<Solicitud> solicitudesEmpleados = new ArrayList<Solicitud>();;
    int cantEmpleados = 0;
    List<Solicitud> solicitudesPendientes = new ArrayList<Solicitud>();;
    int cantPendientes = 0;

    try {
      nombre = area.getNombre();
      solicitudes = area.getSolicitudes();
      solicitudesEmpleados = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.ACEPTADA).toList();
      cantEmpleados = solicitudesEmpleados.size();
      solicitudesPendientes = solicitudes.stream().filter((_emp) -> _emp.getEstado() == SolicitudEstado.PENDIENTE).toList();
      cantPendientes = solicitudesPendientes.size();

    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }
    //TODO Agregar HC total a la vista

    //Nombre:
    //cantidad Empleados:
    //listado empleados
    //Solicitudes Pendientes: <si es facil>
    //Hc Total:

    String finalNombre = nombre;
    int finalCantEmpleados = cantEmpleados;
    int finalCantPendientes = cantPendientes;

    return new ModelAndView(new HashMap<String, Object>(){{
      put("nombreArea", finalNombre);
      //put("empleados", empleados);
      put("cantEmpleados", finalCantEmpleados);
      //put("pendientes", pendientes);
      put("cantPendientes", finalCantPendientes);
      //put("HC", HC); TODO
    }}, "usuarioOrganizacion/detalleArea.hbs");
  }
}
