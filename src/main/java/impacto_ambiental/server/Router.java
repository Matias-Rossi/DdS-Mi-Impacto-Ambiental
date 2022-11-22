package impacto_ambiental.server;

import impacto_ambiental.controllers.*;
import impacto_ambiental.controllers.helpers.PermisoHelper;
import impacto_ambiental.models.entities.notificaciones.Contacto;
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


    private static void configure(){
        recomendacionesController recomendacionesController = new recomendacionesController();
        FactoresDeEmisionController factoresDeEmisionController = new FactoresDeEmisionController();
        ContactoController contactoController = new ContactoController();
        TrayectosController trayectosController = new TrayectosController();
        TramosController tramosController = new TramosController();
        SignUpController signUpController = new SignUpController();
        LoginController loginController = new LoginController();
        HomeController homeController = new HomeController();
        OrganizacionController organizacionController = new OrganizacionController();
        HomeOrganizacionController homeOrganizacionController = new HomeOrganizacionController();
        AreasOrganizacionController areasOrganizacionController = new AreasOrganizacionController();
        GestorAreaController gestorAreaController = new GestorAreaController();
        EmpleadosController empleadosController = new EmpleadosController();
        CargaReportesController cargaReportesController = new CargaReportesController();
        CalcularHcController calcularHCController = new CalcularHcController();
        ReportesOrganizacionController reportesOrganizacionController = new ReportesOrganizacionController();
        HomeAdminController homeAdminController = new HomeAdminController();
        AgenteSectorialController agenteSectorialController = new AgenteSectorialController();
        SolicitudesCompartirTramoController solicitudesCompartirTramoController = new SolicitudesCompartirTramoController();
        ForbiddenController forbiddenController = new ForbiddenController();
        TransportesController transportesController = new TransportesController();
        GeoController geoController = new GeoController();




       // Spark.staticFiles.location("/public");



        Spark.path("/", () -> {
            Spark.get("", homeController::pantallaDeHome, engine);
        });

        Spark.get("/recomendaciones",recomendacionesController::recomendacionesView,engine);

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

        Spark.path("/forbidden", () -> {
            Spark.get("", forbiddenController::mostrarForbidden, engine);
        });


        // ### Miembro ###
        Spark.path("/home", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    response.redirect("/login");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/login");
                    Spark.halt();
                }
            }));

            Spark.get("", homeController::homeUser, engine);
        });
        //## USURIO
        // ### Calcular HC ###
        Spark.path("/calcularhc", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.post("", calcularHCController::calcularHcMiembro);
            Spark.get("", calcularHCController::mostrarHCMiembro, engine);

        });

        Spark.path("/organizaciones", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));


            Spark.get("", organizacionController::mostrarPropias, engine);
            Spark.post("/desvincularse/", organizacionController::desvincularseOrganizacion);
            Spark.get("/vincularse", organizacionController::pantallaVincularse, engine);
            Spark.post ("/vincularse/", organizacionController::vincularseOrganizacion);
        });

       // ### Trayectos ###
        Spark.path("/trayectos", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MIEMBRO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", trayectosController::mostrarPropios, engine);
            Spark.post ("/", trayectosController::guardar);
            Spark.post("/:idTrayecto/delete", trayectosController::eliminar);
        });

        // ### Contacots ###
        Spark.path("/contactos", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", contactoController::mostrarPropios, engine);
            Spark.post ("/", contactoController::cargarContacto);
            Spark.post("/:idContacto/delete", contactoController::eliminarContacto);
        });

        // ### Tramos ###
        Spark.path("/trayectos/:idTrayecto/tramos", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MIEMBRO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", tramosController::mostrarPropios, engine);
            Spark.get("/new", tramosController::pantallaNewTramo, engine);
            Spark.get("/:idTramo/compartir-tramo", tramosController::pantallaCompartirTramo, engine);
            Spark.post("/:idTramo/compartir-tramo", tramosController::compartirCon);
            Spark.post("/new/particular", tramosController::cargarParticular);
            Spark.post("/new/transporte-contratado", tramosController::cargarContratado);
            Spark.post("/new/transporte-publico", tramosController::cargarPublico);
            Spark.post("/new/bicicleta-o-similares", tramosController::cargarbicicleta);
            Spark.post("/new/a-pie", tramosController::cargarpie);
            Spark.post("/:idTramo/delete", tramosController::eliminar);
            //Spark.get("/:idTramo", tramosController::mostrar, engine);
            //Spark.get("/:idTramo/edit", tramosController::editar, engine); //solo te lleva a la pantalla de edit
            Spark.post("/:idTramo/edit", tramosController::modificar);       //lo que realmente lo edita
        });


        // ### Solicitudes ###
        Spark.path("/solicitudes", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.MIEMBRO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", solicitudesCompartirTramoController::mostrar, engine);
            Spark.post("", solicitudesCompartirTramoController::respuestaTramo);
        });



        // ### Organizacion ###
        Spark.path("/homeorg", () -> {

            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.VER, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

             Spark.get("", homeOrganizacionController::pantallaDeHome, engine);
        });

        Spark.path("/areas", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", areasOrganizacionController::mostrarPropias, engine);
            Spark.get("/:idArea", gestorAreaController::mostrar, engine);
            Spark.post("", areasOrganizacionController::guardar);
            Spark.post("/:idArea/delete", areasOrganizacionController::borrar);
        });

        Spark.path("/empleados", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", empleadosController::elegirPantalla, engine);
           Spark.get("/actuales", empleadosController::mostrarEmpleadosActuales, engine);
            Spark.post("/actuales/:idSolicitud/desvincular", empleadosController::desvincular);
           Spark.get("/pendientes", empleadosController::mostrarEmpleadosPendientes, engine);
            //TODO si, seguramente haya q cambiarlos a los siguientes
           Spark.post("/aceptar", empleadosController::aceptarEmpleado);
           Spark.post("/rechazar", empleadosController::rechazarEmpleado);
        });

        Spark.path("/reportes", ()-> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.get("",reportesOrganizacionController::pantallaReportesOrganizacion,engine);
            Spark.get("/composicion",reportesOrganizacionController::hcComposicion,engine);
            Spark.get("/historico",reportesOrganizacionController::hcHistorico,engine);

        });

        Spark.path("/cargaDeReportes", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", cargaReportesController::mostrarVista, engine);
            Spark.post("", "multipart/form-data", cargaReportesController::cargar);
            Spark.get("/mostrarInfo", cargaReportesController::mostrarResultados, engine);
        });

        Spark.path("/calcularHCOrg", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.post("", calcularHCController::calcularHcOrganizacion);
            Spark.get("", calcularHCController::mostrarHcOrganizacion, engine);
        });

        // ### Fin Organizacion ###

        //* ADMINISTRADOR *//

        Spark.path("/admin", ()-> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", homeAdminController::home, engine);
        });
        Spark.path("/factoresDeEmision",()->{
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", factoresDeEmisionController::mostrarFactoresDeEmision,engine);
            Spark.post("/:idFactorDeEmision", factoresDeEmisionController::modificarFactor);
        });

        Spark.path("/setRecomendaciones", ()-> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", recomendacionesController::pantallaRecomendacion, engine);
            Spark.post("", recomendacionesController::setRecomendaciones);
        });


        Spark.path("/gestionar-organizaciones", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.get("", organizacionController::mostrarPantallaAdministrador, engine);
            //Spark.get("/:id", organizacionController::editar, engine); //TODO Edicion de organizaciones desde panel admin
            Spark.post("/crear", signUpController::signUpOrganizacion);
            //Spark.post("/:id/guardar", organizacionController::guardar);
            //Spark.post("/:id/delete", organizacionController::eliminar);
        });
        //* Fin ADMINISTRADOR *//

        //* Agente Sectorial *//
        Spark.path("/agenteSectorial", () -> {
            Spark.before("", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MUNICIPIO_O_DEPARTAMENTO))) {
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MUNICIPIO_O_DEPARTAMENTO))) {
                    System.out.println("ENTRA DEL IF");
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));
            Spark.get("", agenteSectorialController::pantallaAgenteSectorial, engine);
            Spark.get("/HCTotal", agenteSectorialController::hctotal, engine);
            Spark.get("/HCHistorico", agenteSectorialController::hcHistorico, engine);
            Spark.get("/composicionHC", agenteSectorialController::hcComposicion, engine);


            Spark.get("/reportesOrganizacion", agenteSectorialController::desplegarOrganizaciones, engine);
            Spark.get("/reportesOrganizacion/:idOrganizacion", agenteSectorialController::botonesOrganizaciones, engine);
            Spark.get("/reportesOrganizacion/:idOrganizacion/hcHistorico", agenteSectorialController::historicoOrganizacion, engine);
            Spark.get("/reportesOrganizacion/:idOrganizacion/hcComposicion", agenteSectorialController::composicionOrganizacion, engine);

            Spark.get("/hcClasificaciones", agenteSectorialController::clasOrg, engine);
            Spark.get("/hcClasificaciones/:idClasificacion", agenteSectorialController::hcClasificacion, engine);
            Spark.get("/organizaciones", agenteSectorialController::mostrarOrganizaciones, engine);
            Spark.get("/organizaciones/:id", agenteSectorialController::detalleOrganizacion, engine);

            Spark.get("/HCNacional", agenteSectorialController::selectProvincias,engine);
            Spark.post("/HCNacional", agenteSectorialController::solicitarProvincias);
            Spark.get("/HCNacional/ComposicionHC", agenteSectorialController::hcNacional,engine);
        });

        /***** JSONs - AJAX *****/
        /* Transportes */
        Spark.path("/transportes", ()-> {
            Spark.get("/:subtipo", transportesController::lineasSegunTransporte);
            Spark.get("/:subtipo/:idLinea", transportesController::paradasSegunLinea);
        });

        /* Municipios */
        Spark.path("/geo", ()->{
            Spark.get("/:idProvincia", geoController::municipiosSegunProvincia);
        });



        //* Fin Agente Sectorial *//
 //_______________________APIS
/*
        Spark.path("/api/", () -> {
            Spark.get("transportePublico/id/lineas", transportePublicoController::lineasDeTransporte , new JsonTransformer() );
        } );
*/
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

        Spark.get("/login", loginController::prohibido, engine);


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
                    response.redirect("/forbidden");
                    Spark.halt();
                }
            }));

            Spark.before("/*", ((request, response) -> {
                if(!PermisoHelper.usuarioTienePermisos(request, Permiso.VER_SERVICIOS)) {
                    response.redirect("/forbidden");
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
