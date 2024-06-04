package com.pitang.testeTecnico.mapper;

import com.pitang.testeTecnico.model.Carro;
import com.pitang.testeTecnico.model.dto.CarroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarroMapper {

    @Mapping(target = "userId", source = "usuario.id")
    CarroDTO toDto(Carro carro);

    @Mapping(target = "usuario.id", source = "userId")
    Carro toEntity(CarroDTO usuarioDTO);

}
