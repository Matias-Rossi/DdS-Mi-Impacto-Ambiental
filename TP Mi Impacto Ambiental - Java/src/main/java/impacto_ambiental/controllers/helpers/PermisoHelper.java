package impacto_ambiental.controllers.helpers;

import impacto_ambiental.models.entities.usuario.Permiso;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTienePermisos(Request request, Permiso... permisos) {
        return UsuarioLogueadoHelper
                .usuarioLogueado(request)
                .getRol()
                .tenesTodosLosPermisos(permisos);
    }
}
