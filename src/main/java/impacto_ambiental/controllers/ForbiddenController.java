package impacto_ambiental.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ForbiddenController {
    public ModelAndView mostrarForbidden(Request request, Response response) {
        return new ModelAndView(new HashMap<String, Object>(){{

        }}, "prohibido.hbs");
    }
}
