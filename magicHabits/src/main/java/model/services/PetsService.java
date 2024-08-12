package model.services;

import model.entities.Pets;
import model.repositories.PetsRepository;

import java.util.List;

public class PetsService {

    private PetsRepository repository = new PetsRepository();

    public Pets create(Object object) {
        return (Pets) repository.create(object);
    }

    public Pets update(Pets pets) {
    	return (Pets) repository.updateById(pets);
    }
    
    public Pets delete(Pets pets) {
		if (repository.findById(pets.getPetID()) != null)
				repository.delete(pets);
		return null;
	}


    public Pets findById(Long id) {
        return (Pets) repository.findById(id);
    }

    public List<Pets> findAll() {
        return repository.findAll();
    }

	
}
