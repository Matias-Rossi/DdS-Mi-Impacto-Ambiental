package impacto_ambiental.models.repositorios;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.db.Repositorio;
import impacto_ambiental.models.entities.perfil.Area;
import impacto_ambiental.models.entities.perfil.Organizacion;

import java.util.List;

public class RepositorioAreas extends Repositorio {


    public RepositorioAreas(Class tipo) {
        super(tipo);
    }

    public List<Area> listarAreasSegunOrganizacion(Integer idOrganizacion) {
//        return EntityManagerHelper
//                .getEntityManager()
//                .createQueryListResult("from " + Organizacion.class.getName() +" Where id = " && idOrganizacion)
//                .getResultList();
        return null;
    }

    public void guardar(Area nuevaArea) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(nuevaArea);
        EntityManagerHelper.commit();
    }

    public void eliminar(Area area) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().remove(area);
        EntityManagerHelper.commit();
    }

    public void modificar(Area area) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().merge(area);
        EntityManagerHelper.commit();
    }
}
