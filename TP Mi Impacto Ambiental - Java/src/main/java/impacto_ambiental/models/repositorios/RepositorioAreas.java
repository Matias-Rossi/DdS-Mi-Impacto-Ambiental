package impacto_ambiental.models.repositorios;

public class RepositorioAreas extends Repositorio {


    public List<Area> listarAreasSegunOrganizacion(idOrganizacion) {
        return EntityManagerHelper
                .getEntityManager()
                .createQueryListResult("from " + Organizacion.class.getName() +" Where id = " && idOrganizacion)
                .getResultList();
    }

    public void guardar(Area nuevaArea) {
        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(nuevaArea);
        EntityManagerHelper.commit();
    }
}
