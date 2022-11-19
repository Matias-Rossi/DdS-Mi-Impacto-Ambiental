package impacto_ambiental.models.entities.importadorExcel;

import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.models.repositorios.RepositorioOrganizaciones;

public class CargarMediciones extends Thread{

    String path;
    Organizacion organizacion;

    @Override
    public void run(){
        organizacion.cargarMediciones(path);
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        repositorioOrganizaciones.actualizar(organizacion);
    }

    public CargarMediciones(String path, Organizacion organizacion){
        this.path=path;
        this.organizacion=organizacion;
    }

}
