package model.services;

import model.entities.Perfis;

import model.repositories.PerfisRepository;

import java.util.List;

public class PerfisService {

    private PerfisRepository repository = new PerfisRepository();

    public Perfis create(Object object) {
        return (Perfis) repository.create(object);
    }

    public Perfis findById(Long id) {
        return (Perfis) repository.findById(id);
    }

    public Perfis update(Perfis perfis) {
        return (Perfis) repository.updateById(perfis);
    }

    public Perfis delete(Perfis perfis) {
		if (repository.findById(perfis.getId()) != null)
			repository.delete(perfis);
		return null;
	}

    public List<Perfis> findAll() {
        return repository.findAll();
    }
}
