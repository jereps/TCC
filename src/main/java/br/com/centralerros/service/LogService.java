package br.com.centralerros.service;

import br.com.centralerros.entity.Categoria;
import br.com.centralerros.entity.Level;
import br.com.centralerros.entity.Log;
import br.com.centralerros.entity.Usuario;

public interface LogService {
    public Log cadastrarLog(String titulo, Categoria categoria, Level level, String detalhe, String origem, Usuario user);

    public Log cadastrarLogWithNewUser(String titulo, Categoria categoria, Level level,
                                       String detalhe, String origem,String userNome, String userEmail, String userSenha);
}
