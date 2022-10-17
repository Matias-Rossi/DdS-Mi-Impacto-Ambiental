package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.security.password.ValidadorContrasenia;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
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

    public ModelAndView pantallaDeSignUp(Request request, Response response) {
        //return new ModelAndView(null, "signUp.hbs");
        List<MunicipiosODepartamentos> municipiosODepartamentosBuscados = this.repositorioMunicipiosODepartamentos.buscarTodos();
        List<SectorTerritorial> sectoresTerritoriales = this.repositorioSectoresTerritoriales.buscarTodos();
        List<String> prueba = new ArrayList<String>();
        prueba.add("1");
        prueba.add("2");
        prueba.add("1");

       return new ModelAndView(new HashMap<String, Object>(){{
            put("municipiosODepartamentos", municipiosODepartamentosBuscados); //TODO Agregar el key
           put("sectoresTerritoriales", sectoresTerritoriales);
            //put("municicpiosODepartamentos", prueba);
       }}, "signUp.hbs"); //TODO Implementar este .hbs, ya existe el .html


    }

    public Response signUpMiembro(Request request, Response response) {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();
        RepositorioSectoresTerritoriales repositorioSectorTerritorial = new RepositorioSectoresTerritoriales();





        String email = request.queryParams("email");
        String password = request.queryParams("password");
        //Boolean existeUsuario = repositorioUsuarios.existeUsuario(email); //TODO arreglar
        Boolean existeUsuario = false;

        //Comprobación si usuario existe
        if(existeUsuario) {
            //TODO Handlear usuario ya existe
            return response;
        }
        //Validación de contraseña
        if(!validarContrasenia(password)) {
            return response;
        }

        //Creación de usuario
        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        MunicipiosODepartamentos municipio = repositorioMunicipiosODepartamentos.buscar(Integer.parseInt(request.queryParams("municipio")));
        Integer numeracion = Integer.parseInt(request.queryParams("numeracion"));
        String calle = request.queryParams("calle");
        String localidad = request.queryParams("localidad");
        String codPostal = request.queryParams("codPostal");
        String nombre = request.queryParams("nombre");
        String apellido = request.queryParams("apellido");
        String numeroDoc = request.queryParams("numeroDoc");
        String razonsocial = request.queryParams("razonsocial");
        TipoDocumento tipoDoc = TipoDocumento.valueOf(request.queryParams("tipoDoc"));

        //SectorTerritorial sector = repositorioSectorTerritorial.buscar(Integer.parseInt(request.queryParams("sectorTerritorial")));

        Usuario usuario = new Usuario(
                rol,
                email,
                password
        );
        Ubicacion ubi;
        if(rol.esTipo(TipoUsuario.MIEMBRO)){
            ubi = new Ubicacion(municipio, localidad, codPostal, calle, numeracion);
            Miembro miembro = new Miembro(nombre,apellido,tipoDoc,numeroDoc,ubi,email,null,usuario);
            repositorioMiembros.agregar(miembro);
        }
        if(rol.esTipo(TipoUsuario.ORGANIZACION)) {
            //ubi = new Ubicacion(municipio, localidad, codPostal, calle, numeracion);
            //Organizacion organizacion = new Organizacion(null,ubi,razonsocial,tipo,clasificacion,usuario);
            //repositorioOrganizaciones.agregar(organizacion);
        }
        if(rol.esTipo(TipoUsuario.AGENTE)){
            //usuario.solicitarSector(sector);
            //repositorioUsuarios.agregar(usuario);
        }

        response.redirect("/login");
        return response;
    }

    public Response signUpOrganizacion(Request request, Response response) {

        String password = request.queryParams("password");

        //Boolean existeUsuario = repositorioUsuarios.existeUsuario(email); //TODO arreglar
        Boolean existeUsuario = false;

        //Comprobación si usuario existe
        if(existeUsuario) {
            //TODO Handlear usuario ya existe
            return response;
        }
        //Validación de contraseña
        if(!validarContrasenia(password)) {
            return response;
        }

        //Creación de organización
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();

        String email = request.queryParams("email");
        String contrasenia = request.queryParams("contrasenia");

        //TODO Agregar RAZON SOCIAL al form de registro para organizaciones
        String razonSocial = request.queryParams("razonSocial");
        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));

        Ubicacion ubicacion = obtenerUbicacion(request);
        Tipo tipo = Tipo.valueOf(request.queryParams("tipo")); //TODO Agregar selector de TIPO DE ORGANIZACION
        String inputClasificacion = request.queryParams("clasificacion");
        Clasificacion clasificacion = repositorioClasificacion.buscar(Integer.parseInt(inputClasificacion)); //TODO Agregar clasificacion

        Usuario usuario = new Usuario(rol, email, contrasenia);

        Organizacion organizacion = new Organizacion(null, ubicacion, razonSocial, Tipo.GUBERNAMENTAL, clasificacion, usuario);
        repositorioOrganizaciones.agregar(organizacion);

        response.redirect("/login");
        return response;
    }
    public Response signUpAgenteSectorial(Request request, Response response) {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioSectoresTerritoriales repositorioSectoresTerritoriales = new RepositorioSectoresTerritoriales();
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();


        Rol rol = repositorioRoles.obtenerRol(request.queryParams("tipoUsuario"));
        String email = request.queryParams("email");
        String password = request.queryParams("password");

        //Boolean existeUsuario = repositorioUsuarios.existeUsuario(email); //TODO arreglar
        Boolean existeUsuario = false;

        //Comprobación si usuario existe
        if(existeUsuario) {
            //TODO Handlear usuario ya existe
            return response;
        }
        //TODO Validación de contraseña
        /*if(!validarContrasenia(password)) {
            return response;
        }*/

        Integer idSectorTerritorial = Integer.valueOf(request.queryParams("sectorTerritorial"));
        SectorTerritorial sector = repositorioSectoresTerritoriales.buscar(idSectorTerritorial);

        //repositorioProvincias.




        Usuario usuario = new Usuario(
            rol,
            email,
            request.queryParams("password")
        );

        usuario.solicitarSector(sector);
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
