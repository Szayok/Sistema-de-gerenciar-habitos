package controller;

import model.entities.Usuarios;
import model.services.UsuariosService;

import java.util.List;

public class UsuariosController {
    private UsuariosService usuariosService = new UsuariosService();
    
    public Usuarios create(Usuarios usuario) {
        usuariosService.create(usuario);
        return usuario;
    }
    
    public Usuarios updateById(Usuarios usuario) {
        usuariosService.update(usuario);
        return usuario;
    }
    
    public void delete(Usuarios usuario) {
        usuariosService.delete(usuario);
    }
    
    public Usuarios findById(Long id) {
        return usuariosService.findById(id);
    }
    
    public List<Usuarios> findAll() {
        return usuariosService.findAll();
    }
    
    public Usuarios findByEmail(String email) {
        return usuariosService.findByEmail(email);
    }
}
