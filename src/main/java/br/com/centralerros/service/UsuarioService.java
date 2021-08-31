package br.com.centralerros.service;

import br.com.centralerros.entity.Usuario;

public interface UsuarioService {

    public Usuario cadastrarUsuarioWithAutorizacao(String nome, String senha, String email, String autorizacao);

    public Usuario cadastrarUsuario(String nome, String senha,String email);

}
