package br.com.centralerros.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @Email
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "senha")
    private String senha;

    @Column(name = "token")
    private String token;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_autorizacao",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "autorizacao_id") } )
    private Set<Autorizacao> autorizacoes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Autorizacao> getAutorizacoes() {
        return autorizacoes;
    }

    public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
        this.autorizacoes = autorizacoes;
    }

}
