package impacto_ambiental.controllers.helpers;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioUsuarios;
import spark.Request;

public class UsuarioLogueadoHelper {


    public static Usuario usuarioLogueado(Request request) {
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        return repositorioUsuarios.buscar((int)request.session().attribute("id"));
        //return EntityManagerHelper
        //        .getEntityManager()
        //        .find(Usuario.class, request.session().attribute("id"));
    }
}
