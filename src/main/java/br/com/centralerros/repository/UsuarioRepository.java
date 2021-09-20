package br.com.centralerros.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.centralerros.entity.Usuario;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByAutorizacoesNome(String nome);

    Usuario findByEmail(String email);

    Usuario findByToken(String token);

    Usuario findOneByEmail(String email);
}
