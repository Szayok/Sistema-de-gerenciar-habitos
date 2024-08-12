package model.repositories;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;


import model.entities.Usuarios;

public class UsuariosRepository implements BasicCrud {

	EntityManager em = Persistence.createEntityManagerFactory("magicHabits").createEntityManager();

    @Override
    public Object create(Object object) {
        Usuarios novoUsuario = (Usuarios) object;
        em.getTransaction().begin();
        em.persist(novoUsuario);
        em.getTransaction().commit();
        return findById((long) novoUsuario.getIdUsuario()); //catch exception
    }

    @Override
    public Object findById(Long id) {
        try {
            Usuarios usuarioInBd = em.find(Usuarios.class, id);
            return usuarioInBd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object updateById(Object object) {
        Usuarios usuarioUpdate = (Usuarios) object;
        em.getTransaction().begin();
        em.merge(usuarioUpdate);
        em.getTransaction().commit();
        return findById((long) usuarioUpdate.getIdUsuario());
    }

    @Override
    public void delete(Object object) {
        Usuarios usuario = (Usuarios) object;
        em.getTransaction().begin();
        em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
        em.getTransaction().commit();
    }

    public List<Usuarios> findAll() {
        return em.createQuery("SELECT u FROM Usuarios u", Usuarios.class).getResultList();
    }

    public Usuarios findByEmail(String email) {
        try {
            return (Usuarios) em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
} 
}
