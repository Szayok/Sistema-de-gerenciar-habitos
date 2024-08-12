package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Perfis")
public class Perfis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "progressoDiario")
    private String progressoDiario;
    
    @Column(name = "progressoSemanal")
    private String progressoSemanal;
    
    @Column(name = "nomeUsuario")
    private String nomeUsuario;

    @ManyToOne
	@JoinColumn(name = "perfil_fk")
    private Usuarios usuarios;
    // Construtor padrão
    public Perfis() {
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getProgressoSemanal() {
        return progressoSemanal;
    }

    public void setProgressoSemanal(String progressoSemanal) {
        this.progressoSemanal = progressoSemanal;
    }

    public String getProgressoDiario() {
        return progressoDiario;
    }

    public void setProgressoDiario(String progressoDiario) {
        this.progressoDiario = progressoDiario;
    }
}
