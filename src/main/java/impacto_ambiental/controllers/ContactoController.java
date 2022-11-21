package impacto_ambiental.controllers;

import impacto_ambiental.models.entities.notificaciones.Contacto;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.trayecto.Tramo;
import impacto_ambiental.models.entities.trayecto.Trayecto;
import impacto_ambiental.models.repositorios.RepositorioContactos;
import impacto_ambiental.models.repositorios.RepositorioMiembros;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioTrayectos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactoController {

    public ModelAndView mostrarPropios(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();

        Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));
        List<Contacto> contactos = organizacion.getContactos();

        return new ModelAndView(new HashMap<String, Object>(){{
            put("contactos", contactos); //TODO Agregar la key
        }}, "/usuarioOrganizacion/mostrarContactos.hbs"); //TODO Implementar .hbs
    }


    public Response cargarContacto(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones=new RepositorioOrganizaciones();
        Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));

        organizacion.agregarContacto(request.queryParams("telefono"),request.queryParams("email"));
        repositorioOrganizaciones.actualizar(organizacion);

        response.redirect("/contactos");
        return response;
    }

    public Response eliminarContacto(Request request, Response response) {
        RepositorioOrganizaciones repositorioOrganizaciones=new RepositorioOrganizaciones();
        Organizacion organizacion = repositorioOrganizaciones.buscarPorIDUsuario(request.session().attribute("id"));

        RepositorioContactos repositorioContactos = new RepositorioContactos();

        Contacto contacto = repositorioContactos.buscarPorId(Integer.valueOf(request.params("idContacto")));
        repositorioContactos.remover(contacto);

        response.redirect("/contactos");
        return response;
    }
}
