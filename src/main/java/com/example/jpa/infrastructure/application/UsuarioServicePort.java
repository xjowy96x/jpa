package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Usuario;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioServicePort {

    public List<UsuarioOutputDto> getAll();
    public UsuarioOutputDto getById(Integer id) throws Exception;
    public List<UsuarioOutputDto> getByName(String name) throws Exception;
    public UsuarioOutputDto anaidirUsuario(UsuarioInputDto u);
    public UsuarioOutputDto updateById(Integer id, UsuarioInputDto u) throws Exception;
    public void deleteUsuarioById(Integer id);

}
