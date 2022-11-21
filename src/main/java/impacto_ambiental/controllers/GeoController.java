package impacto_ambiental.controllers;

import com.google.gson.Gson;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.repositorios.RepositorioMunicipiosODepartamentos;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
import impacto_ambiental.spark.utils.MunicipioJson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeoController {
    public String municipiosSegunProvincia(Request request, Response response) {
        Gson gson = new Gson();
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

        String idProvincia = request.params("idProvincia");

        Provincia provincia = repositorioProvincias.buscarPorId(Integer.parseInt(idProvincia));

        List<MunicipiosODepartamentos> municipios = provincia.getMunicipiosODepartamentos();

        List<MunicipioJson> municipiosJson = municipios.stream()
                .map(m -> new MunicipioJson(m)).
                collect(Collectors.toList());

        return gson.toJson(municipiosJson);
    }

}


