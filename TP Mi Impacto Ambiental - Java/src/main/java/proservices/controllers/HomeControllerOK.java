package proservices.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeControllerOK {

    public ModelAndView pantallaDeHome(Request request, Response response) {
        return new ModelAndView(null, "homeOK.hbs");
    }


}


