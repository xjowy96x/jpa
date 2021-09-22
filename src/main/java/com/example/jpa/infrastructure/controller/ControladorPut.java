package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControladorPut {

    @Autowired
    UsuarioService usuarioService;

    @PutMapping("{id}")
    public UsuarioOutputDto updateById(@PathVariable("id") Integer id, @Valid @RequestBody UsuarioInputDto u) throws Exception{
        return usuarioService.updateById(id,u);
    }

}
