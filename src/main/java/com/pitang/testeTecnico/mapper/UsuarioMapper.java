package com.pitang.testeTecnico.mapper;

import com.pitang.testeTecnico.model.Usuario;
import com.pitang.testeTecnico.model.dto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toDto(Usuario usuario);

    Usuario toEntity (UsuarioDTO usuarioDTO);

}