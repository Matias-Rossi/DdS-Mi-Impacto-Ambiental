package impacto_ambiental.models.repositorios;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.perfil.Organizacion;
import impacto_ambiental.db.Repositorio;

import java.util.List;

public class RepositorioOrganizaciones extends Repositorio<Organizacion> {
  public RepositorioOrganizaciones() {
    super(Organizacion.class);
  }

  public List<Organizacion> getOrganizacionesPorClasificacion(Clasificacion clasificacion){
    return null;
  }
}
