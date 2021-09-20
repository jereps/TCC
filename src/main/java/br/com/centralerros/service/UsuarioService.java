package br.com.centralerros.service;

import br.com.centralerros.dto.UsuarioDTO;
import br.com.centralerros.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public Usuario cadastrarUsuarioWithAutorizacao(String nome, String senha, String email, String autorizacao);

    public Usuario cadastrarUsuario(String nome, String senha,String email);

    public Usuario cadastrarUsuarioWithAutorizacao(UsuarioDTO userDTO);

    public List<Usuario> findAll();

    public Usuario findByEmail(String email);

    public Usuario atualizarUsuario(UsuarioDTO userDto);

}
