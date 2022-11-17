package impacto_ambiental.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeOrganizacionController {
  public ModelAndView pantallaDeHome(Request request, Response response) {
    return new ModelAndView(null, "homeOrganizacion.hbs");
  }
}
