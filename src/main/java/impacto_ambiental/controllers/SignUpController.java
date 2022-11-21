package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.security.password.ValidadorContrasenia;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.TipoUsuario;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignUpController {

    private RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
    private  RepositorioSectoresTerritoriales repositorioSectoresTerritoriales = new RepositorioSectoresTerritoriales();
    private  RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();
    private RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

    public ModelAndView pantallaDeSignUp(Request request, Response response) {
        //return new ModelAndView(null, "signUp.hbs");
        List<MunicipiosODepartamentos> municipiosODepartamentosBuscados = this.repositorioMunicipiosODepartamentos.buscarTodos();
        List<SectorTerritorial> sectoresTerritoriales = this.repositorioSectoresTerritoriales.buscarTodos();
        List<Clasificacion> clasificaciones = this.repositorioClasificacion.buscarTodos();
        List<Provincia> provincias = repositorioProvincias.buscarTodos();

       return new ModelAndView(new HashMap<String, Object>(){{
            put("municipiosODepartamentos", municipiosODepartamentosBuscados); //TODO Agregar el key
           put("provincias", provincias);
           put("sectoresTerritoriales", sectoresTerritoriales);
           put("clasificaciones", clasificaciones);
       }}, "signUp.hbs"); //TODO Implementar este .hbs, ya existe el .html


    }

    public Response signUpMiembro(Request request, Response response) {
        //REPOS
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        //Creación de usuario
        //Datos para el Usuario
        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        //Comprobación si usuario existe
        Boolean existeUsuario = repositorioUsuarios.existeUsuario(email);
        if(existeUsuario) {
            response.body("Ya existe el usuario");
            System.out.println("Ya existe el usuario");
            response.redirect("/signup");
            return response;
        }
        //Validación de contraseña
        if(!validarContrasenia(password)) {
            response.body("Contrasenia invalida");
            System.out.println("Contrasenia invalida");
            response.redirect("/signup");
            return response;
        }
        Usuario usuario = new Usuario(rol, email, password);

        //Creación de la Organizacion
        //Datos para la Organizacion
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String numeroDoc = request.queryParams("numeroDoc");
        TipoDocumento tipoDoc = TipoDocumento.valueOf(request.queryParams("tipoDoc"));
        Ubicacion ubicacion = obtenerUbicacion(request);

        Miembro miembro = new Miembro(nombre,apellido,tipoDoc,numeroDoc,ubicacion,email,null,usuario);
        repositorioMiembros.agregar(miembro);

        response.redirect("/login");
        return response;
    }

    public Response signUpOrganizacion(Request request, Response response) {
        //REPOS
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();



        //Datos para el Usuario
        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        //Comprobación si usuario existe
        Boolean existeUsuario = repositorioUsuarios.existeUsuario(email); //TODO arreglar
        if(existeUsuario) {
            response.body("Ya existe el usuario");
            response.redirect("/signup");
            return response;
        }
        //Validación de contraseña
        if(!validarContrasenia(password)) {
            response.body("Contrasenia invalida");
            response.redirect("/signup");
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

        response.redirect("/login");
        return response;
    }

    public Response signUpAgenteSectorial(Request request, Response response) {
        //REPOS
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioSectoresTerritoriales repositorioSectoresTerritoriales = new RepositorioSectoresTerritoriales();
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();


        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        Boolean existeUsuario = repositorioUsuarios.existeUsuario(email);

        //Comprobación si usuario existe
        if(existeUsuario) {
            response.body("Ya existe el usuario");
            response.redirect("/signup");
            return response;
        }
        //Validación de contraseña
        if(!validarContrasenia(password)) {
            response.body("Contrasenia invalida");
            response.redirect("/signup");
            return response;
        }

        Usuario usuario = new Usuario(rol, email, password);

        //Datos Sector territorial
        Integer idSectorTerritorial = Integer.valueOf(request.queryParams("sectorTerritorial"));
        SectorTerritorial sector = repositorioSectoresTerritoriales.buscar(idSectorTerritorial);
        usuario.solicitarSector(sector); //TODO ver que onda
        usuario.agregarSector(sector);

        repositorioUsuarios.agregar(usuario);

        response.redirect("/login");
        return response;
    }


    private Ubicacion obtenerUbicacion(Request request){
        RepositorioMunicipiosODepartamentos repositorioMoD = new RepositorioMunicipiosODepartamentos();
        String direccion = request.queryParams("calle");
        Integer numeracion = Integer.valueOf(request.queryParams("numeracion"));
        String codigoPostal = request.queryParams("codPostal");
        MunicipiosODepartamentos municipio = repositorioMoD.buscar(Integer.parseInt(request.queryParams("municipio")));;
        String localidad = request.queryParams("localidad");
        
        return new Ubicacion(municipio,localidad,codigoPostal,direccion,numeracion);
    }

    private Boolean validarContrasenia(String contrasenia) {
        ValidadorContrasenia validador = new ValidadorContrasenia();
        return validador.validar(contrasenia, ValidadorContrasenia.completo);
    }
}
