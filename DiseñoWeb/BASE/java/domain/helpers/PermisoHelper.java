package impacto_ambiental.helpers;

import impacto_ambiental.models.entities.usuarios.Permiso;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTienePermisos(Request request, Permiso ... permisos) {
        return UsuarioLogueadoHelper
                .usuarioLogueado(request)
                .getRol()
                .tenesTodosLosPermisos(permisos);
    }
}
