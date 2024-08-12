package model.repositories;


import model.entities.Pets;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import java.util.List;

public class PetsRepository implements BasicCrud {

    
	EntityManager em = Persistence.createEntityManagerFactory("magicHabits").createEntityManager();
    
    @Override
    public Pets create(Object object) {
        
        Pets novoPet = (Pets) object;
        try {
            em.getTransaction().begin();
            em.persist(novoPet);
            em.getTransaction().commit();
            return findById(novoPet.getPetID());
        } finally {
            em.close();
        }
    }
    @Override
    public Pets findById(Long id) {
        
        try {
        	Pets petInBd = em.find(Pets.class, id);
            return petInBd;
        } finally {
            em.close();
        }
    }
    @Override
    public Pets updateById(Object object) {
    	Pets petUpdate = (Pets) object;
        try {
            em.getTransaction().begin();
            Pets petInDb = em.find(Pets.class, petUpdate.getPetID());
            if (petInDb != null) {
                petInDb.setNome(petUpdate.getNome());
                petInDb.setNivelFome(petUpdate.getNivelFome());
                petInDb.setNivelFelicidade(petUpdate.getNivelFelicidade());
                em.getTransaction().commit();
                return petInDb;
            }
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }
    }

    public void delete(Object object) {
        Pets pets = (Pets) object;
        em.getTransaction().begin();
        em.remove(em.contains(pets) ? pets : em.merge(pets));
        em.getTransaction().commit();
    }

    public List<Pets> findAll() {
       
        try {
            return em.createQuery("SELECT p from Pets p", Pets.class).getResultList();
        } finally {
            em.close();
        }
    }
}
