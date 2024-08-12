package model.repositories;

import model.entities.Perfis;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class PerfisRepository implements BasicCrud {

    private EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("magichabits").createEntityManager();
    }
    @Override
    public Object create(Object object) {
        EntityManager em = getEntityManager();
        Perfis novoPerfil = (Perfis) object;
        try {
            em.getTransaction().begin();
            em.persist(novoPerfil);
            em.getTransaction().commit();
            return novoPerfil;
        } finally {
            em.close();
        }
    }
    @Override
    public Object findById(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Perfis.class, id);
        } finally {
            em.close();
        }
    }
    @Override
    public Object updateById(Object object) {
        EntityManager em = getEntityManager();
        Perfis perfilInDb = (Perfis) object;
        try {
            em.getTransaction().begin();
            perfilInDb = em.find(Perfis.class, perfilInDb.getId());
            if (perfilInDb != null) {
                perfilInDb.setProgressoSemanal(perfilInDb.getProgressoSemanal());
                perfilInDb.setProgressoDiario(perfilInDb.getProgressoDiario());
                em.getTransaction().commit();
                return perfilInDb;
            }
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }
    @Override
    public void delete(Object object) {
        EntityManager em = getEntityManager();
        Perfis perfilInDb = (Perfis) object;
        try {
            em.getTransaction().begin();
            perfilInDb = em.find(Perfis.class, object);
            if (perfilInDb != null) {
                em.remove(perfilInDb);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
    }

    public List<Perfis> findAll() {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Perfis p", Perfis.class).getResultList();
        } finally {
            em.close();
        }
    }

	
}
