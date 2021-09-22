package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ControladorGet {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioOutputDto> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("{id}")
    public UsuarioOutputDto getById(@PathVariable Integer id) throws Exception{
        return usuarioService.getById(id);
    }

    @GetMapping("name/{name}")
    public List<UsuarioOutputDto> getByName(@PathVariable String name) throws Exception{
        return usuarioService.getByName(name);
    }

}
