package br.com.centralerros.repository;

import br.com.centralerros.entity.Categoria;
import br.com.centralerros.entity.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

    public List<Log> findByUsuarioNome(String nome);

    public List<Log> findByUsuarioEmail(String email);

    public Log findOneByCategoria(Enum categoria);

    Page<Log> findAll(Pageable pageable);




    Page<Log> findByCategoria(Enum categoria, Pageable pageable);

    Page<Log> findByCategoriaAndLevel(Enum categoria, Enum level, Pageable pageable);

    @Query(" select l from Log l where categoria = :categoria and detalhe like %:descricao% ")
    Page<Log> findByCategoriaWithDescricao(@Param("categoria") Enum categoria,
                                           @Param("descricao") String descricao, Pageable pageable);

    @Query(" select l from Log l where categoria = :categoria and origem like %:origem%")
    Page<Log> findByCategoriaWithOrigem(@Param("categoria") Enum categoria,
                                        @Param("origem") String origem, Pageable pageable);


}
