package impacto_ambiental.server;

import impacto_ambiental.controllers.*;
import impacto_ambiental.controllers.helpers.PermisoHelper;
import impacto_ambiental.models.entities.usuario.Accion;
import impacto_ambiental.models.entities.usuario.Alcance;
import impacto_ambiental.models.entities.usuario.Objeto;
import impacto_ambiental.models.entities.usuario.Permiso;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import impacto_ambiental.spark.utils.BooleanHelper;
import impacto_ambiental.spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }


    private static void configure() {
        TrayectosController trayectosController = new TrayectosController();
        TramosController tramosController = new TramosController();
        SignUpController signUpController = new SignUpController();
        LoginController loginController = new LoginController();
        HomeController homeController = new HomeController();
        OrganizacionController organizacionController = new OrganizacionController();
       // Spark.staticFiles.location("/public");

        // ### Miembro ###
        Spark.path("/", () -> {
            Spark.get("", homeController::pantallaDeHome, engine);
        });

        Spark.path("/login", () -> {
            Spark.get("", loginController::pantallaDeLogin, engine);
            Spark.post("", loginController::login);
        });

        Spark.path("/logout", () -> {
            Spark.post("", loginController::logout);

        });
        Spark.path("/signup", () -> {
            Spark.get("", signUpController::pantallaDeSignUp, engine);
            Spark.post("/miembro", signUpController::signUpMiembro);
            Spark.post("/organizacion", signUpController::signUpOrganizacion);
            Spark.post("/agente_sectorial", signUpController::signUpAgenteSectorial);
        });

        Spark.path("/home", () -> {
            Spark.get("", homeController::homeUser, engine);
        });

        Spark.path("/organizaciones", () -> {


            Spark.before("", ((request, response) -> {
                System.out.println("ANTES DEL IF");

                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/prohibido");
                    Spark.halt();
                }
                System.out.println("SALE DEL IF");
            }));

            Spark.before("/*", ((request, response) -> {
                System.out.println("ANTES DEL IF");
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/prohibido");
                    Spark.halt();
                }
                System.out.println("SALE DEL IF");
            }));


            Spark.get("", organizacionController::mostrarPropias, engine);
            Spark.post("/desvincularse/", organizacionController::desvincularseOrganizacion);
            Spark.get("/vincularse", organizacionController::pantallaVincularse, engine);
            Spark.post ("/vincularse/", organizacionController::vincularseOrganizacion);
        });
       // ### Trayectos ###
        Spark.path("/trayectos", () -> {
            Spark.get("", trayectosController::mostrarPropios, engine);
            Spark.post ("/", trayectosController::guardar);
            // Spark.post("/:id/delete", trayectosController::deleteTrayecto);
        });
        // ### Tramos ###

        Spark.path("/trayectos/:idTrayecto/tramos", () -> {
            Spark.get("", tramosController::mostrarPropios, engine);
            Spark.get("/new", tramosController::pantallaNewTramo, engine);
            Spark.post("/new/particular", tramosController::cargarParticular);
            Spark.post("/new/transporte-contratado", tramosController::cargarContratado);
            Spark.post("/new/transporte-publico", tramosController::cargarPublico);
            Spark.post("/new/bicicleta-o-similares", tramosController::cargarbicicleta);
            Spark.post("/new/a-pie", tramosController::cargarpie);
            //Spark.get("/:idTramo", tramosController::mostrar, engine);
            //Spark.get("/:idTramo/edit", tramosController::editar, engine); //solo te lleva a la pantalla de edit
            Spark.post("/:id/edit", tramosController::modificar);       //lo que realmente lo edita
        });

/*

        Spark.path("/perfil", () -> {
            Spark.get("", perfilController::mostrar, engine);
        });

        Spark.path("/solicitudes", () -> {
            Spark.get("", solicitudesMiembroController::mostrarTodos);
        });

        Spark.path("/organizaciones/:idOrganizacion/calculadorHc", () -> {
            Spark.get("", calculadorHcController::mostrar ); //TODO ?
        });

        Spark.path("/organizaciones", () -> {
           Spark.get("", organizacionesMiembroController::mostrarTodas);
           Spark.get("/:idOrganizacion", organizacionesMiembroController::mostrar);
        });

        Spark.path("/trayectos", () -> {
            Spark.get("", trayectosController::mostrarTodos, engine);
            Spark.get("/:idTrayecto", trayectosController::mostrar, engine);
            Spark.get("/:idTrayecto/editar", trayectosController::vistaEditar, engine);
            Spark.post("/:idTrayecto", trayectosController::modificar);
            Spark.post("", trayectosController::guardar);
        });

        Spark.path("/trayectos/:idTrayecto/tramos", () -> {
            Spark.get("", tramosController::mostrarTodos, engine);
            Spark.get("/:idTramo", tramosController::mostrar, engine);
            Spark.get("/:idTramo/editar", tramosController::vistaEditar, engine);
            Spark.post("/:idTramo", tramosController::modificar);
            Spark.post("", tramosController::guardar);
        });

        Spark.get("/prohibido", loginController::prohibido, engine);


        // ## Organizacion ##

        Spark.path("/organizacion", () -> {
            Spark.get("", organizacionController::mostrar);
            Spark.get("/areas", organizacionController::mostrarAreas);
        });
        */
        /* PROSERVICES
        Spark.path("/servicios", () -> {
            Spark.before("", AuthMiddleware::verificarSesion);
            Spark.before("/*", AuthMiddleware::verificarSesion);

            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, Permiso.VER_SERVICIOS)) {
                    response.redirect("/prohibido");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, Permiso.VER_SERVICIOS)) {
                    response.redirect("/prohibido");
                    Spark.halt();
                }
            }));

            Spark.get("", serviciosController::mostrarTodos, engine);
            Spark.get("/crear", serviciosController::crear, engine);
            Spark.get("/:id", serviciosController::mostrar, engine);
            Spark.get("/:id/editar", serviciosController::editar, engine);
            Spark.post("/:id", serviciosController::modificar);
            Spark.post("", serviciosController::guardar);

            Spark.path("/:id/tareas", () -> {
                Spark.get("", tareasController::mostrarTodos, engine);
            });
        });
    }

    private static void metodosDePrueba() {
        SaludoController saludoController = new SaludoController();

        //Spark.get("/hola", (request, response) -> "Hola mundo");
        Spark.get("/hola", saludoController::holaMundoBasico);

        //QUERY PARAMS
        //Spark.get("/saludar", ((request, response) -> "Hola " + request.queryParams("apellido")));
        Spark.get("/saludar", saludoController::saludarPorApellido);

        //ROUTE PARAM
        //Spark.get("/saludo/:nombre", ((request, response) -> "Hola " + request.params("nombre")));
        Spark.get("/saludo/:nombre", saludoController::saludarPorNombre);
    }
    */


    }

}
