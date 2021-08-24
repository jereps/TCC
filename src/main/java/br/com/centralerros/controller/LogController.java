package br.com.centralerros.controller;

import br.com.centralerros.dto.LogDTO;
import br.com.centralerros.dto.LogRespondeDTO;
import br.com.centralerros.entity.Categoria;
import br.com.centralerros.entity.Level;
import br.com.centralerros.mapper.LogMapper;
import br.com.centralerros.service.LogService;
//import br.com.centralerros.service.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "log")
@Api(tags = {"Log"}, description = "Endpoint para gerenciar logs")
public class LogController {

    private LogService logService;
    private LogMapper mapper;
//    private SecurityService securityService;

    @ApiOperation(value = "Enviar logs com token")
    @PostMapping
    private LogRespondeDTO add(@RequestParam(value = "token") String token, @Valid @RequestBody LogDTO dto) {
        return mapper.map(logService.add(token, dto));
    }

    @GetMapping
    private List<LogRespondeDTO> findAll(Pageable pageable) {
        return logService.findByAll(pageable);
    }

    @GetMapping("/{categoria}")
    public List<LogRespondeDTO> findByCategoria(@PathVariable("categoria") String categoria,
                                                @RequestParam(value = "level", required = false) String level,
                                                @RequestParam(value = "descricao", required = false) String descricao,
                                                @RequestParam(value = "origem", required = false) String origem, Pageable pageable
    ) {
        if (categoria != null && !categoria.equals("")) {
            if (level != null && !level.equals("")) {
                return logService.findByCategoriaAndLevel(Categoria.valueOf(categoria.toUpperCase()), Level.valueOf(level.toUpperCase()), pageable);
            } else if (descricao != null && !descricao.equals("")) {
                return logService.findByCategoriaWithDescricao(Categoria.valueOf(categoria.toUpperCase()), descricao, pageable);
            } else if (origem != null && !origem.equals("")) {
                return logService.findByCategoriaWithOrigem(Categoria.valueOf(categoria.toUpperCase()), origem, pageable);
            }
            try {
                return logService.findByCategoria(Categoria.valueOf(categoria.toUpperCase()), pageable);

            } catch (IllegalArgumentException e) {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

}
