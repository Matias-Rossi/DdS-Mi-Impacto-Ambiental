package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.notificaciones.Recomendacion;
import impacto_ambiental.models.repositorios.RepositorioRecomendaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.awt.geom.RectangularShape;
import java.util.HashMap;

public class recomendacionesController {
    public recomendacionesController(){}


    public ModelAndView recomendacionesView(Request request, Response response){
        RepositorioRecomendaciones repositorioRecomendaciones = new RepositorioRecomendaciones();
        Recomendacion recomendacion = repositorioRecomendaciones.buscarPorId(1);

        return new ModelAndView(new HashMap<String, Object>(){{
            put("recomendacion",recomendacion);
        }}, "recomendaciones.hbs");
    }

    public ModelAndView pantallaRecomendacion(Request request, Response response){

        return new ModelAndView(new HashMap<String, Object>(){{
        }}, "admin/gestionarRecomendaciones.hbs");
    }

    public Response setRecomendaciones(Request request, Response response){

        String titulo = request.queryParams("titulo");
        String subtitulo = request.queryParams("subtitulo");
        String texto = request.queryParams("texto");

        RepositorioRecomendaciones repositorioRecomendaciones = new RepositorioRecomendaciones();
        Recomendacion recomendacion = repositorioRecomendaciones.buscarPorId(1);

        recomendacion.setTitulo(titulo);
        recomendacion.setSubtitulo(subtitulo);
        recomendacion.setTexto(texto);

        repositorioRecomendaciones.actualizar(recomendacion);

        response.redirect("/setRecomendaciones");
        return response;
    }


}
