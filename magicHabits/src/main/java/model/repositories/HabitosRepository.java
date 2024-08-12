package model.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.entities.Habitos;

public class HabitosRepository implements BasicCrud {

    EntityManager em = Persistence.createEntityManagerFactory("magicHabits").createEntityManager();

    @Override
    public Habitos create(Object object) {
        Habitos novoHabito = (Habitos) object;
        em.getTransaction().begin();
        em.persist(novoHabito);
        em.getTransaction().commit();
        em.refresh(novoHabito); // Atualiza o estado do objeto após a persistência
        return novoHabito;
    }

    @Override
    public Object findById(Long id) {
        try {
            Habitos habitosBD = em.find(Habitos.class, id);
            return habitosBD;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object updateById(Object object) {
        Habitos habitosUpdate = (Habitos) object;
        em.getTransaction().begin();
        em.merge(habitosUpdate);
        em.getTransaction().commit();
        return findById(habitosUpdate.getId());
    }

    @Override
    public void delete(Object object) {
        Habitos habitos = (Habitos) object;
        em.getTransaction().begin();
        em.remove(em.contains(habitos) ? habitos : em.merge(habitos));
        em.getTransaction().commit();
    }
    
    public List<Habitos> findall() {
        return em.createQuery("SELECT h FROM Habitos h", Habitos.class).getResultList();
    }
    
    public List<Habitos> findAllByUserId(Long idUsuario) {
        return em.createQuery("SELECT h FROM Habitos h WHERE h.usuario.id = :idUsuario", Habitos.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList();
    }

}
