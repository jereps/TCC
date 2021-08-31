package br.com.centralerros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.centralerros.entity.Autorizacao;

@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao,Integer>{
    public Autorizacao findByNome(String nome);

    public Autorizacao findByUsuariosEmail(String email);

}
