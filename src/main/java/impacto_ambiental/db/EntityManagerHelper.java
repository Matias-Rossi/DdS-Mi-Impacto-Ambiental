package impacto_ambiental.db;

import org.hibernate.jpa.HibernatePersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.spi.PersistenceProvider;
import java.util.List;

public class EntityManagerHelper {

        private static EntityManagerFactory emf;
        private static ThreadLocal<EntityManager> threadLocal; //Permite sincronización

        static {
          try {
              PersistenceProvider provider = new HibernatePersistenceProvider();
            emf = provider.createEntityManagerFactory("db", null);
            threadLocal = new ThreadLocal<>();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        public static EntityManager getEntityManager() {
            if(null == threadLocal){
                threadLocal = new ThreadLocal<>();
            }
          EntityManager em = threadLocal.get();
          if(em == null || !em.isOpen()) {
              if(null == emf){
                  PersistenceProvider provider = new HibernatePersistenceProvider();
                  emf = provider.createEntityManagerFactory("db", null);
              }
            em = emf.createEntityManager();
            threadLocal.set(em);
          }
          return em;
        }

        public static void persist(Object entidad) {
          getEntityManager().persist(entidad);
        }
        public static void beginTransaction() {
        EntityManager em = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        if(!tx.isActive()){
            tx.begin();
        }
        }

        public static void commit() {
          EntityManager em = EntityManagerHelper.getEntityManager();
          EntityTransaction et = em.getTransaction();
          if(et.isActive()){
            et.commit();
          }
        }

        public static void update(Object entity) {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            em.close();
        }


        public static void delete(Object entity) {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.contains(entity) ? entity : em.merge(entity));
            em.getTransaction().commit();
            em.close();
        }

        public static List<Object> createQueryListResult(String query) {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            List<Object> result = em.createQuery(query).getResultList();
            em.getTransaction().commit();
            em.close();
            return result;
        }
        public static Object createQuery(String query) {
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            Object result = em.createQuery(query).getSingleResult();
            em.getTransaction().commit();
            em.close();
            return result;
        }
        public static Object find(Class clase, Object id) {
            EntityManager em = getEntityManager();;
            Object entity = em.find(clase, id);
            em.close();
            return entity;
        }


        public static List findAll(Class clase) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
            EntityManager em = emf.createEntityManager();
            List entities = em.createQuery("SELECT e FROM " + clase.getSimpleName() + " e").getResultList();
            em.close();
            return entities;
        }

}
