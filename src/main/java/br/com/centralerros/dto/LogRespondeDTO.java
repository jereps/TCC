package br.com.centralerros.dto;

import br.com.centralerros.entity.Categoria;
import br.com.centralerros.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","usuario","origem", "titulo","detalhe","createdAt","categoria","level"})
public class LogRespondeDTO {

    private Long id;
    private String origem;
    private String titulo;
    private String detalhe;
    private LocalDateTime createdAt;
    private Categoria categoria;
    private Level level;
    private UsuarioRespontaDTO usuario;

}
