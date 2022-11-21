package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Tipo;
import impacto_ambiental.models.entities.transporte.*;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.*;
import java.util.stream.Collectors;

public class TramosController {
  private RepositorioTramos repTramos;

  public TramosController() {
    this.repTramos = new RepositorioTramos();
  }

  //Mostrar todos
  public ModelAndView mostrarPropios(Request request, Response response) {


    RepositorioTrayectos repositorioTrayectos = new RepositorioTrayectos();


    String idTrayecto = request.params("idtrayecto");
    System.out.println("######### ID: " + Integer.valueOf(idTrayecto));

    Trayecto trayecto = repositorioTrayectos.buscarPorId(Integer.valueOf(idTrayecto));
    System.out.println("$$$$$$$ ID: " + trayecto.getId());
    List<Tramo> tramosBuscados = trayecto.getTramos();
    List<Tramo> pasarTramos = new ArrayList<>();
    if(!tramosBuscados.equals(null)) {
      pasarTramos.addAll(tramosBuscados);
    }


    return new ModelAndView(new HashMap<String, Object>(){{
      put("idTrayecto",idTrayecto);
      put("tramos", pasarTramos); //TODO Agregar la key
    }}, "/trayectos/tramos/tramos.hbs"); //TODO Implementar .hbs
  }


  //Mostrar individual
  public ModelAndView mostrar(Request request, Response response) {
    String id = request.params("id");
    Tramo tramoBuscado = repTramos.buscar(Integer.valueOf(id));

    return new ModelAndView(new HashMap<String, Object>(){{
      put("", tramoBuscado); //TODO Agregar el key
    }}, ".hbs"); //TODO Implementar .hbs
  }

  public ModelAndView pantallaNewTramo(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransportes = new RepositorioSubTipoTransportes();
    RepositorioParadas repositorioParadas = new RepositorioParadas();
    RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();

    List<SubTipoTransporte> subtipos = repositorioSubTipoTransportes.buscarTodos();
    System.out.println("despues  del id subtipos");


    List<MunicipiosODepartamentos> municipiosODepartamentosBuscados = repositorioMunicipiosODepartamentos.buscarTodos();
    List<SubTipoTransporte> particulares = subtipos.stream().filter(a->a.getTipo().equals(TipoTransporte.TIPO_PARTICULAR)).collect(Collectors.toList());
    List<SubTipoTransporte> contratados = subtipos.stream().filter(a->a.getTipo().equals(TipoTransporte.TIPO_CONTRATADO)).collect(Collectors.toList());
    List<SubTipoTransporte> publicos = subtipos.stream().filter(a->a.getTipo().equals(TipoTransporte.TIPO_PUBLICO)).collect(Collectors.toList());
    List<Parada> paradas = repositorioParadas.buscarTodos();



    return new ModelAndView(new HashMap<String, Object>(){{
      put("idTrayecto",request.params("idTrayecto"));
      put("municipiosODepartamentos", municipiosODepartamentosBuscados);
      put("particulares",particulares);
      put("contratados",contratados);
      put("transportesPublicos", publicos);
      put("paradas",paradas);
    }}, "/trayectos/tramos/tramoNew.hbs"); //TODO Implementar .hbs
  }

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
    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  public Response cargarParticular(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransporte = new RepositorioSubTipoTransportes();
    RepositorioTransportes repositorioTransportes = new RepositorioTransportes();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    RepositorioParticular repositorioParticular = new RepositorioParticular();

    Integer subtipo = Integer.valueOf( request.queryParams("idSubTipo"));
    String combustible =request.queryParams("tipoCombustible");

    System.out.println(subtipo);
    System.out.println(combustible);

    Transporte transporte = repositorioParticular.encontrar(subtipo,combustible);

    System.out.println("Lo encontre 2");

    Ubicacion salida = this.obtenerSalidaUbicacion(request);
    Ubicacion llegada = this.obtenerLlegadaUbicacion(request);

    System.out.println("imprimiendo  1");

    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));

    trayectoAModificar.aniadirNuevoTramo(salida,llegada,transporte);

    repTrayectos.actualizar(trayectoAModificar);



    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  public Response cargarContratado(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransporte = new RepositorioSubTipoTransportes();
    RepositorioTransportes repositorioTransportes = new RepositorioTransportes();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();

    Integer subtipo = Integer.valueOf( request.queryParams("idSubTipo"));

    Transporte transporte = repositorioTransportes.encontrar(subtipo);
    Ubicacion salida = this.obtenerSalidaUbicacion(request);
    Ubicacion llegada = this.obtenerLlegadaUbicacion(request);


    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));
    trayectoAModificar.aniadirNuevoTramo(salida,llegada,transporte);
    repTrayectos.actualizar(trayectoAModificar);



    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  public Response cargarPublico(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransporte = new RepositorioSubTipoTransportes();
    RepositorioTransportes repositorioTransportes = new RepositorioTransportes();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    RepositorioParadas repositorioParadas = new RepositorioParadas();
    RepositorioUbicaciones repositorioUbicaciones = new RepositorioUbicaciones();

    Integer paradaSalidaId = Integer.valueOf(request.queryParams("paradaSalidaId"));
    Integer paradaLlegadaId = Integer.valueOf(request.queryParams("paradaLlegadaId"));

    Parada paradaSalida = repositorioParadas.buscar(paradaSalidaId);
    Parada paradaLlegada = repositorioParadas.buscar(paradaLlegadaId);

   Ubicacion salida = paradaSalida.getUbicacion();
    Ubicacion llegada = paradaLlegada.getUbicacion();

    Transporte transporte = paradaSalida.getLinea().getTransporte();

    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));
    trayectoAModificar.aniadirNuevoTramo(salida,llegada,transporte);
    repTrayectos.actualizar(trayectoAModificar);



    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }
  public Response cargarbicicleta(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransporte = new RepositorioSubTipoTransportes();
    RepositorioTransportes repositorioTransportes = new RepositorioTransportes();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    RepositorioParadas repositorioParadas = new RepositorioParadas();
    RepositorioUbicaciones repositorioUbicaciones = new RepositorioUbicaciones();

    Transporte transporte = repositorioTransportes.buscar(2);
    Ubicacion salida = this.obtenerSalidaUbicacion(request);
    Ubicacion llegada = this.obtenerLlegadaUbicacion(request);

    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));
    trayectoAModificar.aniadirNuevoTramo(salida,llegada,transporte);
    repTrayectos.actualizar(trayectoAModificar);

    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  public Response cargarpie(Request request, Response response) {

    RepositorioSubTipoTransportes repositorioSubTipoTransporte = new RepositorioSubTipoTransportes();
    RepositorioTransportes repositorioTransportes = new RepositorioTransportes();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    RepositorioParadas repositorioParadas = new RepositorioParadas();
    RepositorioUbicaciones repositorioUbicaciones = new RepositorioUbicaciones();

    Transporte transporte = repositorioTransportes.buscar(1);
    Ubicacion salida = this.obtenerSalidaUbicacion(request);
    Ubicacion llegada = this.obtenerLlegadaUbicacion(request);

    String idTrayecto = request.params("idTrayecto");
    Trayecto trayectoAModificar = repTrayectos.buscar(Integer.valueOf(idTrayecto));
    trayectoAModificar.aniadirNuevoTramo(salida,llegada,transporte);
    repTrayectos.actualizar(trayectoAModificar);

    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }


  public Response compartirCon(Request request, Response response) {

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioTramos repositorioTramos = new RepositorioTramos();



    String idTramo = request.params("idTramo");
    String idMiembro = request.queryParams("idMiembro");
    Miembro miembro = repositorioMiembros.buscarPorId(Integer.valueOf(idMiembro));

    Tramo tramo = repositorioTramos.buscar(Integer.valueOf(idTramo));

    tramo.compartirTramo(miembro);

    repositorioMiembros.actualizar(miembro);


    response.redirect("/trayectos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }
  public Response eliminar(Request request, Response response) {


    RepositorioTramos repositorioTramos = new RepositorioTramos();
    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioTrayectos repositorioTrayectos = new RepositorioTrayectos();


    String idTramo = request.params("idTramo");
    String idTrayecto = request.params("idTrayecto");

    Trayecto trayecto = repositorioTrayectos.buscar(Integer.valueOf(idTrayecto));
    Tramo tramo = repositorioTramos.buscar(Integer.valueOf(idTramo));
    trayecto.eliminarTramo(tramo);

    repositorioTrayectos.actualizar(trayecto);

    response.redirect("/trayectos/"+idTrayecto+"/tramos"); //TODO Revisar si la url de redirección es correcta
    return response;
  }

  public ModelAndView pantallaCompartirTramo(Request request, Response response) {

    RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    RepositorioTrayectos repTrayectos = new RepositorioTrayectos();
    String idTrayecto = request.params("idTrayecto");
    String idTramo = request.params("idTramo");
    System.out.println("ID TRAYECTO");
    System.out.println(idTrayecto);
    Trayecto trayecto = repTrayectos.buscar(Integer.valueOf(idTrayecto));

    Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));

    Set<Miembro> miembros = trayecto.getOrganizacionesxtrayectos().stream().map(ot -> ot.getOrganizacion().getMiembros()).flatMap(Collection::stream).collect(Collectors.toSet());

    miembros.remove(unMiembro);



    return new ModelAndView(new HashMap<String, Object>(){{
      put("miembros", miembros);
      put("idTrayecto",idTrayecto);
      put("idTramo",idTramo);//TODO Agregar el key
    }}, "trayectos/tramos/tramoCompartido.hbs");
  }


  private Ubicacion obtenerSalidaUbicacion(Request request){
    RepositorioMunicipiosODepartamentos repositorioMoD = new RepositorioMunicipiosODepartamentos();
    String direccion = request.queryParams("calle");
    Integer numeracion = Integer.valueOf(request.queryParams("numeracion"));
    String codigoPostal = request.queryParams("codPostal");
    MunicipiosODepartamentos municipio = repositorioMoD.buscar(Integer.parseInt(request.queryParams("municipio")));;
    String localidad = request.queryParams("localidad");

    return new Ubicacion(municipio,localidad,codigoPostal,direccion,numeracion);
  }

  private Ubicacion obtenerLlegadaUbicacion(Request request){
    RepositorioMunicipiosODepartamentos repositorioMoD = new RepositorioMunicipiosODepartamentos();
    String direccion = request.queryParams("calle2");
    Integer numeracion = Integer.valueOf(request.queryParams("numeracion2"));
    String codigoPostal = request.queryParams("codPostal2");
    MunicipiosODepartamentos municipio = repositorioMoD.buscar(Integer.parseInt(request.queryParams("municipio2")));;
    String localidad = request.queryParams("localidad2");

    return new Ubicacion(municipio,localidad,codigoPostal,direccion,numeracion);
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
