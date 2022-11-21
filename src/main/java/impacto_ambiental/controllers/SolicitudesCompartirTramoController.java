package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.repositorios.RepositorioMiembros;
import impacto_ambiental.models.repositorios.RepositorioTramos;
import impacto_ambiental.models.repositorios.RepositorioTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class SolicitudesCompartirTramoController {
    public SolicitudesCompartirTramoController(){

    }

    public ModelAndView mostrar(Request request, Response response) {
        int id_usuario = request.session().attribute("id");
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

        Miembro miembro = repositorioMiembros.buscarPorIDUsuario(id_usuario);

        List<Trayecto> trayectos = miembro.getTrayectos();
        List<Tramo> tramos = miembro.getTramosCompartidosAAceptar();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("tramos", tramos);
            put("trayectos",trayectos);//TODO Agregar el key
        }}, "solicitudes/usuarioSolicitudes.hbs"); //TODO Implementar .hbs
    }

    public Response respuestaTramo(Request request, Response response) {
        RepositorioTrayectos repositorioTrayectos = new RepositorioTrayectos();
        RepositorioTramos repositorioTramos = new RepositorioTramos();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

        String idTramo = request.queryParams("idTramo");
        String idTrayecto = request.queryParams("idTrayecto");
        //Boolean respuesta = Boolean.parseBoolean(request.params("respuesta"));

        Tramo tramo = repositorioTramos.buscarPorId(Integer.valueOf(idTramo));
        Trayecto trayecto = repositorioTrayectos.buscarPorId(Integer.valueOf(idTrayecto));
        Miembro miembro = repositorioMiembros.buscarPorIDUsuario(request.session().attribute("id"));


        miembro.gestionarTramosCompartidos(tramo,trayecto,true);
        repositorioMiembros.actualizar(miembro);

        response.redirect("/home"); //TODO Revisar si la url de redirección es correcta
        return response;
    }

}
