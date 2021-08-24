package br.com.centralerros.service;

import br.com.centralerros.dto.LogDTO;
import br.com.centralerros.dto.LogRespondeDTO;
import br.com.centralerros.mapper.LogMapper;
import br.com.centralerros.repository.LogRepository;
import br.com.centralerros.entity.Log;
import br.com.centralerros.entity.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LogService {

    private UsuarioServiceImpl usuarioService;
    private LogMapper mapper;
    private LogRepository logRepository;


    public Log add(String token, LogDTO dto) {
        Usuario usuario = usuarioService.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token invalido"));

        Log log = mapper.map(dto);
        log.setUsuario(usuario);
        return logRepository.saveAndFlush(log);
    }

    @PreAuthorize("isAuthenticated")
    public List<LogRespondeDTO> findByAll(Pageable pageable) {
        return mapper.map(logRepository.findAll(pageable).getContent());
    }

    @PreAuthorize("isAuthenticated")
    public List<LogRespondeDTO> findByCategoria(Enum categoria, Pageable pageable) {
        return mapper.map(logRepository.findByCategoria(categoria, pageable).getContent());
    }

    @PreAuthorize("isAuthenticated")
    public List<LogRespondeDTO> findByCategoriaAndLevel(Enum categoria, Enum level, Pageable pageable) {
        return mapper.map(logRepository.findByCategoriaAndLevel(categoria, level, pageable).getContent());
    }

    @PreAuthorize("isAuthenticated")
    public List<LogRespondeDTO> findByCategoriaWithDescricao(Enum categoria, String descricao, Pageable pageable) {
        return mapper.map(logRepository.findByCategoriaWithDescricao(categoria, descricao, pageable).getContent());
    }

    @PreAuthorize("isAuthenticated")
    public List<LogRespondeDTO> findByCategoriaWithOrigem(Enum categoria, String origem, Pageable pageable) {
        return mapper.map(logRepository.findByCategoriaWithOrigem(categoria, origem, pageable).getContent());
    }
}
