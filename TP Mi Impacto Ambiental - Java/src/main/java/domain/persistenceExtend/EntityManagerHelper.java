package domain.persistenceExtend;

import com.twilio.example.Example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityManagerHelper {

        private static EntityManagerHelper instance = null;

        private EntityManagerHelper() {
        }

        public static EntityManagerHelper getInstance() {
            if (instance == null) {
                instance = new EntityManagerHelper();
            }
            return instance;
        }

        public static void persist(Object entity) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.close();
            emf.close();
        }

        public static void update(Object entity) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            em.close();
            emf.close();
        }


        public static void delete(Object entity) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            em.close();
            emf.close();
        }

        //Quiero inicializar solo un elemento de una lista lazy en el momento que lo necesito
    //solo deberia recuperar el elemento que quiero, no toda la lista
    //lo deseo obtener a partir de un atributo de esa clase y su valor
    //ejemplo: quiero obtener un municipio de una provincia con un nombre determinado
    public static Object getLazyElement(Object entity, String atributo, String valor){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Object result = em.createQuery("SELECT e FROM " + entity.getClass().getSimpleName() + " e WHERE e." + atributo + " = :" + valor).getSingleResult();
        em.getTransaction().commit();
        em.close();
        emf.close();
        return result;
    }
        public static Object createQuery(String query) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Object result = em.createQuery(query).getSingleResult();
            em.getTransaction().commit();
            em.close();
            emf.close();
            return result;
        }
        public static Object find(Class clase, Object id) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            Object entity = em.find(clase, id);
            em.close();
            emf.close();
            return entity;
        }


        public static List findAll(Class clase) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            List entities = em.createQuery("SELECT e FROM " + clase.getSimpleName() + " e").getResultList();
            em.close();
            emf.close();
            return entities;
        }

}
