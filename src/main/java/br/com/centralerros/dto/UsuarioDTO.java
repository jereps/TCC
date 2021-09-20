package br.com.centralerros.dto;

import br.com.centralerros.entity.Autorizacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.stream.Stream;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String senha;

    private Autorizacao autorizacao;

    public String getNome() {
        return nome;
    }

    @JsonIgnore
    public boolean isNull() {
        return Stream.of(nome, email, senha).allMatch(Objects::isNull);
    }
}
