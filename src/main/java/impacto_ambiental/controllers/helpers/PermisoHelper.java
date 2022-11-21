package impacto_ambiental.controllers.helpers;

import impacto_ambiental.models.entities.usuario.Permiso;
import impacto_ambiental.models.entities.usuario.Usuario;
import spark.Request;

public class PermisoHelper {

    public static Boolean usuarioTienePermisos(Request request, Permiso ... permisos) {
        Boolean tienePermisos;
        try {
            tienePermisos = UsuarioLogueadoHelper.usuarioLogueado(request).getRol().tenesTodosLosPermisos(permisos);
        }
        catch (Exception e) {
            tienePermisos = false;
        }

        return tienePermisos;
    }
}