package br.com.centralerros.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotNull
    private String nome;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "senha")
    @NotNull
    private String senha;

    @Column(name = "token")
    private String token;

    // @ManyToMany(fetch = FetchType.EAGER)
    // private Set<Autorizacao> autorizacoes;

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

    // public Set<Autorizacao> getAutorizacoes() {
    //     return autorizacoes;
    // }

    // public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
    //     this.autorizacoes = autorizacoes;
    // }

}
