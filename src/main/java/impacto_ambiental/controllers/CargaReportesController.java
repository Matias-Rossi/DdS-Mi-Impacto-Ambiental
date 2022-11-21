package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.importadorExcel.ActividadBase;
import impacto_ambiental.models.entities.importadorExcel.ApachePOI;
import impacto_ambiental.models.entities.importadorExcel.CargarMediciones;
import impacto_ambiental.models.entities.perfil.Importador;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

import static spark.Spark.staticFiles;

public class CargaReportesController {
  RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();

  public ModelAndView mostrarVista(Request request, Response response) {
    Organizacion organizacion = obtenerOrganizacionSegunIDUsuario(request);
    List<ActividadBase> actividades = organizacion.getActividadesCargadas();
    System.out.println(actividades.size());

    return new ModelAndView(new HashMap<String, Object>() {{
      put("actividades", actividades);
    }}, "usuarioOrganizacion/cargaReportes.hbs"); //TODO
  }

  public Response cargar(Request request, Response response) {
    String path = request.queryParams("fileDeReportes");
    File directorioUploads = new File("upload");
    directorioUploads.mkdir();



    Path tempFile = null;
    try {
      tempFile = Files.createTempFile(directorioUploads.toPath(), "", ".xlsx");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

    try (InputStream input = request.raw().getPart("fileDeReportes").getInputStream()) {
      Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (ServletException | IOException e) {
      throw new RuntimeException(e);
    }

    //Procesar información
    Importador importador = new ApachePOI();
    Organizacion unaOrganizacion = obtenerOrganizacionSegunIDUsuario(request);
    unaOrganizacion.setModuloImportador(importador);
    CargarMediciones carga = new CargarMediciones(tempFile.toString(),unaOrganizacion);
    Thread cargaDeMedicion = new Thread(carga,"cargaAsinc");
    cargaDeMedicion.start();

    response.redirect("/cargaDeReportes");
    return response;
  }

  public ModelAndView mostrarResultados(Request request, Response response) {

    return new ModelAndView(new HashMap<String, Object>() {{
    }}, "usuarioOrganizacion/.hbs"); //TODO
  }
  private Organizacion obtenerOrganizacionSegunIDUsuario(Request request) {
    return repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
  }
}
