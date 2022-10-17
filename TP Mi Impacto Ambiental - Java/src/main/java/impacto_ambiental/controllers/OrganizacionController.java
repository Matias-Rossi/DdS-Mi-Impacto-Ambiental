package impacto_ambiental.controllers;



import impacto_ambiental.models.entities.perfil.Miembro;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.entities.perfil.SolicitudEstado;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.RepositorioMiembros;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;
import impacto_ambiental.models.repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;

public class OrganizacionController {
    private RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
    private RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
    private RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();


    public ModelAndView mostrarTodas(Request request, Response response){
        Usuario unUsuario = repositorioUsuarios.buscar(Integer.valueOf(request.session().attribute("id")) );
        String queryParaBuscarDedeOtroId = "SELECT e FROM " + unUsuario.getClass() + " WHERE id_="+unUsuario.getId();
        Miembro unMiembro = repositorioMiembros.buscar(queryParaBuscarDedeOtroId);

        List<Organizacion> organizacionesDeMiembro =   unMiembro.getSolicitudes().stream()
                .filter(unaSolicitud-> unaSolicitud.getEstado() == SolicitudEstado.ACEPTADA )
                .map(unaSolicitud-> unaSolicitud.getArea().getOrganizacion() ).toList() ;



        return new ModelAndView(new HashMap<String, Object>(){{
            put("organizaciones",organizacionesDeMiembro );
        }}, "oraganizaciones.hbs"); //TODO Implementar este .hbs, ya existe el .html

    }

}
