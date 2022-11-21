package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.Solicitud;
import impacto_ambiental.models.entities.perfil.SolicitudEstado;
import impacto_ambiental.models.repositorios.RepositorioFactorDeEmision;
import impacto_ambiental.models.repositorios.RepositorioSolicitudes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class FactoresDeEmisionController {

    public ModelAndView mostrarFactoresDeEmision(Request request, Response response) {
        RepositorioFactorDeEmision repo = new RepositorioFactorDeEmision();
        List<FactorDeEmision> factores = repo.buscarTodos();


        return new ModelAndView(new HashMap<String, Object>(){{
            put("factores", factores);
        }}, "/admin/fe.hbs");
    }


    public Response modificarFactor(Request request, Response response) {
        String idFactor = request.params("idFactorDeEmision");
        RepositorioFactorDeEmision repo= new RepositorioFactorDeEmision();
        FactorDeEmision factor = repo.buscarPorId(Integer.valueOf(idFactor));
        Double valorNuevo = Double.valueOf(request.queryParams("valor"));
        factor.setFactorEmision(valorNuevo);
        repo.actualizar(factor);
        response.redirect("/factoresDeEmision");
        return response;
    }


}
