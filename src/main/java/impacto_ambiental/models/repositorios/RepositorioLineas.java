package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.Linea;

public class RepositorioLineas extends Repositorio<Linea> {

  public RepositorioLineas() {
    super(Linea.class);
  }
}
