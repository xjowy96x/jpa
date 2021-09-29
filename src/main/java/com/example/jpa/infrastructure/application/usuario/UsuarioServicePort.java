package com.example.jpa.infrastructure.application.usuario;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.usuario.input.UsuarioInputDto;
import com.example.jpa.infrastructure.dto.usuario.output.UsuarioOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;


public interface UsuarioServicePort {

    public List<UsuarioOutputDto> getAll();
    public UsuarioOutputDto getById(Integer id);
    public List<UsuarioOutputDto> getByName(String name);
    public UsuarioOutputDto addUsuario(UsuarioInputDto u, Errors errors) throws BeanUnprocesableException;
    public UsuarioOutputDto updateById(Integer id, UsuarioInputDto u, Errors errors) throws BeanNotFoundException,BeanUnprocesableException;
    public void deleteUsuarioById(Integer id) throws BeanNotFoundException;

}
