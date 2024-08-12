package model.entities;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Long idUsuario;
    
    @Column(name = "nomeUsuario")
    private String nomeUsuario;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "senha")
    private String senha;
    
    @Column(name = "dataNascimento")
    private String dataNascimento;
    
    @Column(name = "expTotal")
    private Long expTotal;

    @Column(name = "nivel")
    private short nivel;
    
    @Column(name = "totalMoedas")
    private Float totalMoedas;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pets> pets;

    // Construtor padrão
    public Usuarios() {
    }

    // Construtor com todos os atributos
    public Usuarios(Long idUsuario, String nomeUsuario, String email, String senha, String dataNascimento,
                    Long expTotal, short nivel, Float totalMoedas, List<Pets> pets) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.expTotal = expTotal;
        this.nivel = nivel;
        this.totalMoedas = totalMoedas;
        this.pets = pets;
    }

    // Getters e Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Long getExpTotal() {
        return expTotal;
    }

    public void setExpTotal(Long expTotal) {
        this.expTotal = expTotal;
    }

    public short getNivel() {
        return nivel;
    }

    public void setNivel(short nivel) {
        this.nivel = nivel;
    }

    public Float getTotalMoedas() {
        return totalMoedas;
    }

    public void setTotalMoedas(Float totalMoedas) {
        this.totalMoedas = totalMoedas;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }
}
