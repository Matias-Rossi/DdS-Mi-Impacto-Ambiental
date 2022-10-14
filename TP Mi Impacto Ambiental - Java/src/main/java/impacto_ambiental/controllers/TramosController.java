package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioTramos;
import impacto_ambiental.models.repositorios.RepositorioTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TramosController {
  private RepositorioTramos repTramos;

  public TramosController() {
    this.repTramos = new RepositorioTramos();
  }

  //Mostrar todos
  public ModelAndView mostrarTodos(Request request, Response response) {
    String idTrayecto = request.params("idTrayecto");
    List<Tramo> tramosBuscados = repTramos.listarTramosDeTrayecto(Integer.valueOf(idTrayecto));

    return new ModelAndView(new HashMap<String, Object>(){{
      put("", tramosBuscados); //TODO Agregar la key
    }}, ".hbs"); //TODO Implementar .hbs
  }


  //Mostrar individual
  public ModelAndView mostrar(Request request, Response response) {
    String id = request.params("id");
    Tramo tramoBuscado = repTramos.buscar(Integer.valueOf(id));

    return new ModelAndView(new HashMap<String, Object>(){{
      put("", tramoBuscado); //TODO Agregar el key
    }}, ".hbs"); //TODO Implementar .hbs
  }


  //Vista de edicion
  public ModelAndView vistaEditar(Request request, Response response) {
    String idTramo = request.params("idTramo");

    Tramo tramoBuscado = repTramos.buscar(idTramo);

    return new ModelAndView(new HashMap<String, Object>(){{
      put("", tramoBuscado); //TODO Agregar el key
    }}, ".hbs"); //TODO Implementar .hbs
  }

  //TODO Ver como lo tratamos con los trayectos.
  //Aplicación de la edición (modificación)
  public Response modificar(Request request, Response response) {
    String idTramo = request.params("idTramo");
    Tramo tramoAModificar = repTramos.buscar(Integer.valueOf(idTramo));

    asignarParametros(tramoAModificar, request);
    repTramos.actualizar(tramoAModificar);
    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }


  //Vista de creación
  public ModelAndView vistaCrear(Request request, Response response) {
    return new ModelAndView(null, ".hbs"); //TODO Implementar .hbs
  }

  //Instanciación y creación del nuevo tramo
  public Response guardar(Request request, Response response) {
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));

    String salida = request.params("salida");
    String llegada = request.params("llegada");
    String transporte = request.params("transporte");

    //TODO: Buscar forma de codificar las ubicaciones y transportes en los queryparams para convertirlos acá

    //trayectoAModificar.aniadirNuevoTramo(salida, llegada, transporte);

    repTrayectos.actualizar(trayectoAModificar);
    response.redirect("/tramos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }





  private void asignarParametros(Tramo trayecto, Request request) {
    /*TODO Parseo de Ubicaciones
    if(request.queryParams("partida") != null) {
      trayecto.setPartida();
    }
    if(request.queryParams("llegada") != null) {
      trayecto.setLlegada();
    }
    if(request.queryParams("medioDeTransporte") != null) {
      trayecto.setMedioDeTransporte();
    }
    if(request.queryParams("integrantes") != null) {
      trayecto.setIntegrantes();
    }
    //TODO Calculamos distancia con GeoDDS?
    //TODO Como tratamos Factor de Emision?
     */
  }

}
