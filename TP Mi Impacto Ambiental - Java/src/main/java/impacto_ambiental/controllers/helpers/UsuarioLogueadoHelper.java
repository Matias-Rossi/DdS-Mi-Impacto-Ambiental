package impacto_ambiental.controllers.helpers;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.usuario.Usuario;
import spark.Request;

public class UsuarioLogueadoHelper {

    public static Usuario usuarioLogueado(Request request) {
        return EntityManagerHelper
                .getEntityManager()
                .find(Usuario.class, request.session().attribute("id"));
    }
}
