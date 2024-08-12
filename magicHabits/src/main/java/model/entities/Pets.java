package model.entities;

import javax.persistence.*;

@Entity
@Table(name = "Pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petID")
    private Long petID;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nivelFome")
    private int nivelFome;

    @Column(name = "nivelFelicidade")
    private int nivelFelicidade;

    @ManyToOne
    @JoinColumn(name = "usuarioID")
    private Usuarios usuario;

    // Construtor padrão
    public Pets() {
    }

    // Construtor com todos os atributos
    public Pets(Long petID, String nome, int nivelFome, int nivelFelicidade, Usuarios usuario) {
        this.petID = petID;
        this.nome = nome;
        this.nivelFome = nivelFome;
        this.nivelFelicidade = nivelFelicidade;
        this.usuario = usuario;
    }

    public Pets(String nome, int nivelFome, int nivelFelicidade, Usuarios usuario) {
        this.nome = nome;
        this.nivelFome = nivelFome;
        this.nivelFelicidade = nivelFelicidade;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Long getPetID() {
        return petID;
    }

    public void setPetID(Long petID) {
        this.petID = petID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNivelFome() {
        return nivelFome;
    }

    public void setNivelFome(int nivelFome) {
        this.nivelFome = nivelFome;
    }

    public int getNivelFelicidade() {
        return nivelFelicidade;
    }

    public void setNivelFelicidade(int nivelFelicidade) {
        this.nivelFelicidade = nivelFelicidade;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
