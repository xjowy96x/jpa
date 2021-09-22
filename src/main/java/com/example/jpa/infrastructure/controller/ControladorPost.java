package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ControladorPost {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public UsuarioOutputDto anaidirUsuario(@Valid @RequestBody UsuarioInputDto u) {
        return usuarioService.anaidirUsuario(u);
    }

}
