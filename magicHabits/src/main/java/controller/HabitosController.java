package controller;

import java.util.List;

import model.entities.Habitos;
import model.entities.Usuarios; // Importe a classe Usuarios
import model.services.HabitosService;

public class HabitosController {

    private HabitosService habitosService = new HabitosService();

    // Método para criar um hábito associado ao usuário logado
    public Habitos create(Habitos habitos, Usuarios usuarioLogado) {
        return habitosService.create(habitos, usuarioLogado);
    }

    public Habitos updateById(Habitos habitos) {
        return habitosService.update(habitos);
    }

    public void delete(Habitos habitos) {
        habitosService.delete(habitos);
        System.out.println("Hábito deletado com sucesso.");
    }

    public Habitos findById(Long id) {
        return habitosService.findById(id);
    }

    public List<Habitos> findAll() {
        return habitosService.findAll();
    }

    public List<Habitos> findAllByUserId(Long idUsuario) {
        return habitosService.findAllByUserId(idUsuario);
    }
}
