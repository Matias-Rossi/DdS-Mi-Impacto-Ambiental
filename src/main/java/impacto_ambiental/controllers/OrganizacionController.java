package impacto_ambiental.controllers;



import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrganizacionController {
    private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
    private RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    private RepositorioAreas repositorioAreas = new RepositorioAreas();
    private RepositorioSolicitudes repositorioSolicitudes =  new RepositorioSolicitudes();

    public ModelAndView mostrarPropias(Request request, Response response){


        System.out.println("QUIERO MOSTRAR");

        Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));

        System.out.println(unMiembro.getId());

        List<Solicitud> solicitudes = repositorioSolicitudes.buscarSolicitudesAceptadasPorIDMiembro(unMiembro.getId());

        List<Area> areas = solicitudes.stream().map(solicitud -> solicitud.getArea()).collect(Collectors.toList());

        List<AreaHc> areasHc = solicitudes.stream().map(sols->new AreaHc(sols)).collect(Collectors.toList());


//
//        List<Organizacion> organizacionesDeMiembro =   unMiembro.getSolicitudes().stream()
//                .filter(unaSolicitud-> unaSolicitud.getEstado() == SolicitudEstado.ACEPTADA )
//                .map(unaSolicitud-> unaSolicitud.getArea().getOrganizacion() ).toList() ;


        return new ModelAndView(new HashMap<String, Object>(){{
            put("areasHc",areasHc );
        }}, "/organizaciones/organizaciones.hbs");

    }

            //Spark.get("/organiazciones/vincularse", organizacionController::pantallaVincularse, engine);

    public ModelAndView pantallaVincularse(Request request, Response response){

        List<Area> todasLasAreas = this.repositorioAreas.buscarTodos() ;

        //TODO FILTRAR LAS QUE NO PERTENECEN AL MIEMBRO

        return new ModelAndView(new HashMap<String, Object>(){{
                    put("areas",todasLasAreas );
        }}, "/organizaciones/vincularse.hbs");
    }
    //Spark.post ("/organiazciones/vincularse/:id", organizacionController::vincularseOrganizacion, engine);
    public Response vincularseOrganizacion(Request request, Response response){
        //RECUPERO EL AREA
        Integer idArea = Integer.valueOf(request.queryParams("area"));
        Area area = this.repositorioAreas.buscar(idArea);
        //RECUPERO EL MIEMBRO
        Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));

        //LO DAMOS DE ALTA EN AREA Y ACTUALIZAMOS BD
        unMiembro.darseAltaEnOrganizacion(area);
       repositorioMiembros.actualizar(unMiembro);

        response.redirect("/organizaciones");
        return response;
    }

    //TODO como hacemos para desvincularnos sin romper, por que si cambiamos la solicitud a rechazado perdemos los hc de los trayectos anteriores
    //Spark.post("/organiazciones/:id/desvincularse", organizacionController::desvincularse, engine);
    public Response desvincularseOrganizacion(Request request, Response response){
        //RECUPERO EL AREA
        Integer idSolicitud = Integer.valueOf(request.queryParams("solicitud"));
        //Area area = this.repositorioAreas.buscar(idArea);
        Solicitud solicitud = this.repositorioSolicitudes.buscar(idSolicitud);
        //RECUPERO EL MIEMBRO
        //Miembro unMiembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));

        //LO DESVINCULAMES
        solicitud.setEstado(SolicitudEstado.DESVINCULADO);

        repositorioSolicitudes.actualizar(solicitud);
        //area.desvincularMiembro(unMiembro);

        response.redirect("/organizaciones");
        return response;
    }

    public ModelAndView mostrarPantallaAdministrador(Request request, Response response) {
        List<Organizacion> organizaciones = repositorioOrganizaciones.buscarTodos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones", organizaciones);
        }}, "/admin/gestionOrganizaciones.hbs");
    }

    public Response crear(Request request, Response response) {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();


        //Datos para el Usuario
        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        //Comprobación si usuario existe
        Boolean existeUsuario = repositorioUsuarios.existeUsuario(email); //TODO arreglar. (Es igual a SignUpController)
        if(existeUsuario) {
            response.body("Ya existe el usuario");
            response.redirect("/gestionar-organizaciones");
            return response;
        }

        Usuario usuario = new Usuario(rol, email, password);

        //Creación de organización
        //Datos para la Organizacion
        String razonSocial = request.queryParams("razonSocial");
        Tipo tipo = Tipo.valueOf(request.queryParams("tipo"));
        String inputClasificacion = request.queryParams("clasificacion");
        Clasificacion clasificacion = repositorioClasificacion.buscar(Integer.parseInt(inputClasificacion));
        Ubicacion ubicacion = obtenerUbicacion(request);

        Organizacion organizacion = new Organizacion(null, ubicacion, razonSocial, tipo, clasificacion, usuario);
        repositorioOrganizaciones.agregar(organizacion);

        response.redirect("/gestionar-organizaciones");
        response.body("Organizacion creada");

        return response;
    }

    private Ubicacion obtenerUbicacion(Request request){
        RepositorioMunicipiosODepartamentos repositorioMoD = new RepositorioMunicipiosODepartamentos(); //TODO La vista no muestra municipios
        String direccion = request.queryParams("calle");
        Integer numeracion = Integer.valueOf(request.queryParams("numeracion"));
        String codigoPostal = request.queryParams("codPostal");
        MunicipiosODepartamentos municipio = repositorioMoD.buscar(Integer.parseInt(request.queryParams("municipio")));;
        String localidad = request.queryParams("localidad");

        return new Ubicacion(municipio,localidad,codigoPostal,direccion,numeracion);
    }
}
