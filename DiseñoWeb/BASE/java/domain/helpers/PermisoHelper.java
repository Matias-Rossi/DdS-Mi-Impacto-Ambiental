package proservices.helpers;

import proservices.models.entities.usuarios.Permiso;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTienePermisos(Request request, Permiso ... permisos) {
        return UsuarioLogueadoHelper
                .usuarioLogueado(request)
                .getRol()
                .tenesTodosLosPermisos(permisos);
    }
}
