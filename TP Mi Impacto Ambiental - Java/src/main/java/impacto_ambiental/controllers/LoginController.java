package impacto_ambiental.controllers;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController {

    public ModelAndView pantallaDeLogin(Request request, Response response) {
        return new ModelAndView(null, "login.hbs");
    }

    public Response login(Request request, Response response) {
        try {
            String query = "from "
                    + Usuario.class.getName()
                    + " WHERE usuario = '"
                    + request.queryParams("email")
                    + "' AND contrasenia ='"
                    + request.queryParams("password")
                    + "'";




            Usuario usuario = (Usuario) EntityManagerHelper
                    .getEntityManager()
                    .createQuery(query)
                    .getSingleResult();



            if(usuario != null) {
                request.session(true);
                request.session().attribute("id", usuario.getId());
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
