package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.transporte.Parada;

public class RepositorioParadas extends Repositorio<Parada> {
    public RepositorioParadas(){ super(Parada.class);}
}