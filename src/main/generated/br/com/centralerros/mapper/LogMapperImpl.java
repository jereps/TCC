package br.com.centralerros.mapper;

import br.com.centralerros.dto.LogDTO;
import br.com.centralerros.dto.LogRespondeDTO;
import br.com.centralerros.dto.UsuarioRespontaDTO;
import br.com.centralerros.entity.Log;
import br.com.centralerros.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-08-29T00:50:05-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (Ubuntu)"
)
@Component
public class LogMapperImpl implements LogMapper {

    @Override
    public Log map(LogDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Log log = new Log();

        log.setOrigem( dto.getOrigem() );
        log.setTitulo( dto.getTitulo() );
        log.setDetalhe( dto.getDetalhe() );
        log.setCreatedAt( dto.getCreatedAt() );
        log.setCategoria( dto.getCategoria() );
        log.setLevel( dto.getLevel() );

        return log;
    }

    @Override
    public LogRespondeDTO map(Log log) {
        if ( log == null ) {
            return null;
        }

        LogRespondeDTO logRespondeDTO = new LogRespondeDTO();

        logRespondeDTO.setId( log.getId() );
        logRespondeDTO.setOrigem( log.getOrigem() );
        logRespondeDTO.setTitulo( log.getTitulo() );
        logRespondeDTO.setDetalhe( log.getDetalhe() );
        logRespondeDTO.setCreatedAt( log.getCreatedAt() );
        logRespondeDTO.setUsuario( usuarioToUsuarioRespontaDTO( log.getUsuario() ) );
        logRespondeDTO.setCategoria( log.getCategoria() );
        logRespondeDTO.setLevel( log.getLevel() );

        return logRespondeDTO;
    }

    @Override
    public List<LogRespondeDTO> map(List<Log> logs) {
        if ( logs == null ) {
            return null;
        }

        List<LogRespondeDTO> list = new ArrayList<LogRespondeDTO>( logs.size() );
        for ( Log log : logs ) {
            list.add( map( log ) );
        }

        return list;
    }

    protected UsuarioRespontaDTO usuarioToUsuarioRespontaDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioRespontaDTO usuarioRespontaDTO = new UsuarioRespontaDTO();

        usuarioRespontaDTO.setId( usuario.getId() );
        usuarioRespontaDTO.setNome( usuario.getNome() );
        usuarioRespontaDTO.setEmail( usuario.getEmail() );
        usuarioRespontaDTO.setToken( usuario.getToken() );

        return usuarioRespontaDTO;
    }
}
