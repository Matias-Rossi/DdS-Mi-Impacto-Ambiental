package impacto_ambiental.controllers;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.trayecto.Trayecto;
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

    public ModelAndView pantallaDeSignUp(Request request, Response response) {
        //return new ModelAndView(null, "signUp.hbs");
        List<MunicipiosODepartamentos> municipiosODepartamentosBuscados = this.repositorioMunicipiosODepartamentos.buscarTodos();
        List<String> prueba = new ArrayList<String>();
        prueba.add("1");
        prueba.add("2");
        prueba.add("1");

       return new ModelAndView(new HashMap<String, Object>(){{
            put("municipiosODepartamentos", municipiosODepartamentosBuscados); //TODO Agregar el key
            //put("municicpiosODepartamentos", prueba);
       }}, "signUp.hbs"); //TODO Implementar este .hbs, ya existe el .html


    }

    public Response signUp(Request request, Response response) {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();
        RepositorioSectoresTerritoriales repositorioSectorTerritorial = new RepositorioSectoresTerritoriales();





        String email = request.queryParams("email");
        //Boolean existeUsuario = repositorioUsuarios.existeUsuario(email);

        if(false) {
            //TODO Handlear usuario ya existe
            //return?
            //TODO Comprobaciones de contraseña
        }else {
            //Crear usuario
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
            //Tipo tipo = Tipo.valueOf(request.queryParams("tipo"));
            //Clasificacion clasificacion = repositorioClasificacion.buscar(Integer.parseInt(request.queryParams("clasificacion")));
            //SectorTerritorial sector = repositorioSectorTerritorial.buscar(Integer.parseInt(request.queryParams("sectorTerritorial")));

            System.out.println(numeracion);
            System.out.println(calle);
            System.out.println(localidad);
            System.out.println(codPostal);
            System.out.println(nombre);
            System.out.println(apellido);
            System.out.println(numeroDoc);
            System.out.println(request.queryParams("password"));


            Usuario usuario = new Usuario(
                    rol,
                    email,
                    request.queryParams("password")
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



        }

        response.redirect("/login");
        return response;
    }
}
