package impacto_ambiental.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeAdminController {

  public ModelAndView home(Request request, Response response) {
    return new ModelAndView(null, "admin/homeAdmin.hbs");
  }
}
