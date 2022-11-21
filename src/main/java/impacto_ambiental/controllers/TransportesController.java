package impacto_ambiental.controllers;

import com.google.gson.Gson;
import impacto_ambiental.models.entities.transporte.Linea;
import impacto_ambiental.models.entities.transporte.Parada;
import impacto_ambiental.models.entities.transporte.TransportePublico;
import impacto_ambiental.models.repositorios.RepositorioLineas;
import impacto_ambiental.models.repositorios.RepositorioTransportePublico;
import impacto_ambiental.spark.utils.LineaJson;
import impacto_ambiental.spark.utils.ParadaJson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TransportesController {

  public String lineasSegunTransporte(Request request, Response response) {
    RepositorioTransportePublico repositorioTransportePublico = new RepositorioTransportePublico();
    Gson gson = new Gson();

    String subtipo = request.params("subtipo");

    List<Linea> lineas = repositorioTransportePublico.buscarLineasPorSubtipo(subtipo);
    System.out.print(lineas.get(0).getId());

    //List<LineaJson> lineasJsonizables = new ArrayList<>();

    List<LineaJson> lineasJsonizables = lineas.stream()
        .map(l -> {
          int idLinea = l.getId();
          String nombreLinea = l.getNombre();
          String subtipoLinea = l.getTransporte().getSubTipoTransporte().getSubTipo();
          return new LineaJson(idLinea, nombreLinea, subtipoLinea);
        })
        .collect(Collectors.toList());

    return gson.toJson(lineasJsonizables);
  }

  public String paradasSegunLinea(Request request, Response response) {
    //RepositorioTransportePublico repositorioTransportePublico = new RepositorioTransportePublico();
    RepositorioLineas repositorioLineas = new RepositorioLineas();
    Gson gson = new Gson();

    String idLinea = request.params("idLinea");

    Linea linea = repositorioLineas.buscarPorId(Integer.parseInt(idLinea));

    List<Parada> paradas = linea.getParadas();

    List<ParadaJson> paradasJsonizables = paradas.stream()
        .map( p -> new ParadaJson(p)
        ).collect(Collectors.toList());

    return gson.toJson(paradasJsonizables);
  }
}
