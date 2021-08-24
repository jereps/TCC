package br.com.centralerros.mapper;

import br.com.centralerros.dto.LogDTO;
import br.com.centralerros.dto.LogRespondeDTO;
import br.com.centralerros.entity.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mappings({
            @Mapping(source = "origem", target = "origem"),
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "detalhe", target = "detalhe"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "level", target = "level"),
    })
    Log map(LogDTO dto);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "origem", target = "origem"),
            @Mapping(source = "titulo", target = "titulo"),
            @Mapping(source = "detalhe", target = "detalhe"),
            @Mapping(source = "createdAt", target = "createdAt"),
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "categoria", target = "categoria"),
            @Mapping(source = "level", target = "level"),
    })
    LogRespondeDTO map(Log log);

    List<LogRespondeDTO> map(List<Log> logs);

}
