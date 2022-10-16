package impacto_ambiental.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static org.apache.http.client.methods.RequestBuilder.put;

public class PerfilController {

    public ModelAndView mostrar(Request request, Response response) {
        String idPerfil = request.params("id");
        return null;
    }
}
