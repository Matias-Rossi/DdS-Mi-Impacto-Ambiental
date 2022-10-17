package impacto_ambiental.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

    public ModelAndView pantallaDeHome(Request request, Response response) {
        return new ModelAndView(null, "home.hbs");
    }
    public ModelAndView homeUser(Request request, Response response) {
        return new ModelAndView(null, "homeUser.hbs");
    }

}


