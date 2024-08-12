package model.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "habitos")
public class Habitos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habitoID")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "experiencia")
    private double experiencia;
    
    @Column(name = "moedaHabi")
    private double moedaHabi;
    
    @Column(name = "dataVencimento")
    private Date dataVencimento;
    
    @Column(name = "completa")
    private boolean completa;
    
    @ManyToOne
    @JoinColumn(name = "IdUsuario")
    private Usuarios usuario;

    // Construtor padrão
    public Habitos() {
    }

    // Construtor com todos os atributos
    public Habitos(Long id, String titulo, String descricao, double experiencia, double moedaHabi, Date dataVencimento, boolean completa, Usuarios usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.experiencia = experiencia;
        this.moedaHabi = moedaHabi;
        this.dataVencimento = dataVencimento;
        this.completa = completa;
        this.usuario = usuario;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public double getMoedaHabi() {
        return moedaHabi;
    }

    public void setMoedaHabi(double moedaHabi) {
        this.moedaHabi = moedaHabi;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
