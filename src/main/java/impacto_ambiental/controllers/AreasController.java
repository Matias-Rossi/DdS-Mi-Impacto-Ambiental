package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.repositorios.RepositorioAreas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AreasController {
  private RepositorioAreas repositorio;

  //LISTAR TODAS, MOSTRAR UN AREA EN PARTICULAR, GUARDAR, VISTA PARA CREAR, MODIFICAR, VISTA PARA MODIFICAR, ELIMINAR


  public void TrayectosController() {
    this.repositorio = new RepositorioAreas();
  }

  //Mostrar todas las areas de la organizacion
  public ModelAndView mostrarTodas(Request request, Response response) {
    String idOrganizacion = request.params("idOrganizacion");
    //List<Area> areasOrganizacion = repositorio.listarAreasSegunOrganizacion(Integer.valueOf(idOrganizacion));

    return new ModelAndView(new HashMap<String, Object>(){{
      //put("", areasOrganizacion); //TODO Agregar el key
    }}, "areasOrganizacion.hbs"); //TODO Implementar este .hbs, ya existe el .html
  }

  //DEVOLVER LA PANTALLA QUE PERMITE CREAR UNA NUEAV AREA
  public ModelAndView crear(Request request, Response response) {
    return new ModelAndView(null, ".hbs"); //TODO .hbs
  }

  // METODO QUE INSTANCIA UNA NUEVA AREA Y LA GUARDA EN LA BASE DE DATOS
  public Response guardar(Request request, Response response) {
    Area nuevaArea = new Area();
    this.asignarParametros(nuevaArea, request);
    //this.repositorio.guardar(nuevaArea);
    response.redirect("/"); //TODO redireccionar a Ruta correspondiente
    return response;
  }
  private void asignarParametros(Area nuevaArea, Request request) {
    if(request.queryParams("nombre") != null) {
      nuevaArea.setNombre(request.queryParams("nombre"));
      Organizacion organizacionCorrespondiente   ; //TODO  como recupero la organizacion
     // nuevaArea.setOrganizacion(organizacionCorrespondiente);
    }
  }
}

