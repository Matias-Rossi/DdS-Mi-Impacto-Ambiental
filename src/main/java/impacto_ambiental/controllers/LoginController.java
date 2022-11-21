package impacto_ambiental.controllers;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

            String email = request.queryParams("email");
            String password = request.queryParams("password");
            Usuario usuario = repositorioUsuarios.obtenerUsuarioSegunCredenciales(email, password);

            if(usuario != null) {
                request.session(true);
                request.session().attribute("id", usuario.getId());
                switch(usuario.getRol().getTipoUsuario()) {
                    case AGENTE -> response.redirect("/agenteSectorial");
                    case MIEMBRO -> response.redirect("/home");
                    case ORGANIZACION -> response.redirect("/homeorg");
                    case ADMINISTRADOR -> response.redirect("/admin"); //TODO
                }
                response.redirect("/home");
            }
            else {
                response.redirect("/login");
            }
        }
        catch (Exception ex) {
            response.redirect("/login");
        }
        return response;
    }

    public Response logout(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/");
        return response;
    }

    public ModelAndView prohibido(Request request, Response response) {
        return new ModelAndView(null, "prohibido.hbs");
    }
}
