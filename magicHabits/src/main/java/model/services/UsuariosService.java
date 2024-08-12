package model.services;

import java.util.List;


import model.entities.Usuarios;
import model.repositories.UsuariosRepository;

public class UsuariosService {

    private UsuariosRepository repository = new UsuariosRepository();

    public Usuarios create(Object object) {
        return (Usuarios) repository.create(object);
    }

    public Usuarios update(Usuarios usuario) {
    	return (Usuarios) repository.updateById(usuario);
    }
    
    public Usuarios delete(Usuarios usuario) {
		if (repository.findById(usuario.getIdUsuario()) != null)
				repository.delete(usuario);
		return null;
	}


    public Usuarios findById(Long id) {
        return (Usuarios) repository.findById(id);
    }

    public List<Usuarios> findAll() {
        return repository.findAll();
    }

    public Usuarios findByEmail(String email) {
        return repository.findByEmail(email);
    }

}
