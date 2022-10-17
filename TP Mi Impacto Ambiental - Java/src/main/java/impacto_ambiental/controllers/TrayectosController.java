package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioMiembros;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioTrayectos;
import impacto_ambiental.models.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TrayectosController {
  private RepositorioTrayectos repositorio = new RepositorioTrayectos();
  private RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
  private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

  public TrayectosController() {

  }

  //Mostrar todos
  //Spark.get("", trayectosController::mostrarTodos, engine);

  public ModelAndView mostrarTodos(Request request, Response response) {

    Usuario unUsuario = repositorioUsuarios.buscar(Integer.valueOf(request.session().attribute("id")) );
    String queryParaBuscarDedeOtroId = "SELECT e FROM " + unUsuario.getClass() + " WHERE id_="+unUsuario.getId();
    Miembro unMiembro = repositorioMiembros.buscar(queryParaBuscarDedeOtroId);


    List<Trayecto> trayectosDelMiembro = unMiembro.getTrayectos();

    return new ModelAndView(new HashMap<String, Object>(){{
      put("trayectos", trayectosDelMiembro); //TODO Agregar el key
    }}, "trayecto/Trayectos.hbs"); //TODO Implementar este .hbs, ya existe el .html
  }

  //Spark.post ("/:id/add", trayectosController::addTrayecto);
  //Spark.post("/:id/delete", trayectosController::deleteTrayecto);
  //Mostrar individual
  public ModelAndView mostrar(Request request, Response response) {
    String idTrayecto = request.params("idTrayecto");

    Trayecto trayectoBuscado = repositorio.buscar(idTrayecto);

    return new ModelAndView(new HashMap<String, Object>(){{
      put("trayecto", trayectoBuscado); //TODO Agregar el key
    }}, "detalleTrayecto.hbs"); //TODO Implementar este .hbs, ya existe el .html
  }

  //Vista de edicion
//  public ModelAndView vistaEditar(Request request, Response response) {
//    String idTrayecto = request.params("idTrayecto");
//
//    Trayecto trayectoBuscado = repositorio.buscar(idTrayecto);
//
//    return new ModelAndView(new HashMap<String, Object>(){{
//      put("", trayectoBuscado); //TODO Agregar el key
//    }}, ".hbs"); //TODO Implementar este .hbs, ya existe el .html
//  }
//
//  //Aplicación de la edición (modificación)
//  public Response modificar(Request request, Response response) {
//    String idTrayecto = request.params("idTrayecto");
//    Trayecto trayectoAModificar = repositorio.buscar(Integer.valueOf(idTrayecto));
//
//    asignarParametros(trayectoAModificar, request);
//    repositorio.actualizar(trayectoAModificar);
//    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
//    return response;
//  }

  //Vista de creación
  public ModelAndView vistaCrear(Request request, Response response) {
    return new ModelAndView(null, ".hbs"); //TODO Implementar .hbs
  }

  //Instanciación y creación del nuevo trayecto
  public Response guardar(Request request, Response response) {
    Trayecto trayecto = new Trayecto();
    this.asignarParametros(trayecto, request);
    this.repositorio.agregar(trayecto);
    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  private void asignarParametros(Trayecto trayecto, Request request) {
    //TODO ¿Los tramos se tratan directamente desde TramosController?
    if(request.queryParams("anio") != null) {
      trayecto.setAnio(Integer.valueOf(request.queryParams("anio")));
    }
    if(request.queryParams("semestre") != null) {
      trayecto.setSemestre(Integer.valueOf(request.queryParams("semestre")));
    }
    if(request.queryParams("diasAlMes") != null) {
      trayecto.setDiasAlMes(Integer.valueOf(request.queryParams("diasAlMes")));
    }
    if(request.queryParams("descripcion") != null) {
      trayecto.setDescripcion(request.queryParams("descripcion"));
    }
    if(request.queryParams("organizaciones") != null) { //Formato: ?organizaciones=<id1>,<id2>,...
      RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();

      List<String> idOrganizaciones = Arrays.asList(request.queryParams("organizaciones").split("\\s*,\\s*")); //IDs de organizaciones separados por comas
      List<Organizacion> organizaciones = idOrganizaciones.stream().map(repositorioOrganizaciones::buscar).collect(Collectors.toList());
      //trayecto.setOrganizaciones(organizaciones);
    }
  }
}

