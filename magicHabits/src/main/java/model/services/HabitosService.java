package model.services;

import model.entities.Habitos;
import model.entities.Usuarios;
import model.repositories.HabitosRepository;

import java.util.List;

public class HabitosService {

    private HabitosRepository repository = new HabitosRepository();

    // Método para criar um hábito associado ao usuário logado
    public Habitos create(Habitos habitos, Usuarios usuarioLogado) {
        habitos.setUsuario(usuarioLogado); // Vincula o usuário logado ao hábito
        return repository.create(habitos);
    }

    public Habitos findById(Long id) {
        return (Habitos) repository.findById(id);
    }

    public Habitos update(Habitos habitos) {
        return (Habitos) repository.updateById(habitos);
    }

    public Habitos delete(Habitos habitos) {
        if (repository.findById(habitos.getId()) != null)
            repository.delete(habitos);
        return null;
    }

    public List<Habitos> findAll() {
        return repository.findall();
    }
    public List<Habitos> findAllByUserId(Long idUsuario) {
        return repository.findAllByUserId(idUsuario);
    }
}
