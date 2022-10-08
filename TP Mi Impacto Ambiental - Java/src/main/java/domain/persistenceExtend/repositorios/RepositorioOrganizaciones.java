package domain.persistenceExtend.repositorios;

import domain.perfil.Organizacion;
import domain.persistenceExtend.Repositorio;

public class RepositorioOrganizaciones extends Repositorio<Organizacion> {
  public RepositorioOrganizaciones() {
    super(Organizacion.class);
  }
}
