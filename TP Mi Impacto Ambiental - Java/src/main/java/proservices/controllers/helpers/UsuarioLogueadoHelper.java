package proservices.controllers.helpers;

import proservices.db.EntityManagerHelper;
import proservices.models.entities.usuario.Usuario;
import spark.Request;

public class UsuarioLogueadoHelper {

    public static Usuario usuarioLogueado(Request request) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, request.session().attribute("id"));
    }
}
