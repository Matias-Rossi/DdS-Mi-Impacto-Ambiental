package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.perfil.Area;
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

  public void guardar(Organizacion nuevaOrganizacion) {
    EntityManagerHelper.beginTransaction();
    EntityManagerHelper.getEntityManager().persist(nuevaOrganizacion);
    EntityManagerHelper.commit();
  }

}
