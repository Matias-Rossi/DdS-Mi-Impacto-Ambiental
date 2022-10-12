package proservices.models.repositorios;

import proservices.models.entities.perfil.Clasificacion;
import proservices.models.entities.perfil.Organizacion;
import proservices.db.Repositorio;

import java.util.List;

public class RepositorioOrganizaciones extends Repositorio<Organizacion> {
  public RepositorioOrganizaciones() {
    super(Organizacion.class);
  }

  public List<Organizacion> getOrganizacionesPorClasificacion(Clasificacion clasificacion){
    return null;
  }
}
