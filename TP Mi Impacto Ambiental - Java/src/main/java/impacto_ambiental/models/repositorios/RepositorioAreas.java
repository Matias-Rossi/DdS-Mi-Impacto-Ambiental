package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;

import java.util.List;

public class RepositorioAreas extends Repositorio<Area> {


    public RepositorioAreas() {
        super(Area.class);
    }

 /*   public List<Area> listarAreasSegunOrganizacion(Integer idOrganizacion) {
//        return EntityManagerHelper
//                .getEntityManager()
//                .createQueryListResult("from " + Organizacion.class.getName() +" Where id = " && idOrganizacion)
//                .getResultList();
        return null;
    }

  */
}
