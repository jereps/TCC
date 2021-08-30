package br.com.centralerros.mapper;

import br.com.centralerros.dto.UsuarioDTO;
import br.com.centralerros.dto.UsuarioRespontaDTO;
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
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioRespontaDTO map(Usuario usuario) {
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

    @Override
    public List<UsuarioRespontaDTO> map(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioRespontaDTO> list = new ArrayList<UsuarioRespontaDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( map( usuario ) );
        }

        return list;
    }

    @Override
    public Usuario map(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setNome( dto.getNome() );
        usuario.setEmail( dto.getEmail() );
        usuario.setSenha( dto.getSenha() );

        return usuario;
    }
}
