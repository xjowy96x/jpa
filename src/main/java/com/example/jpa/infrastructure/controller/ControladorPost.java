package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.application.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class ControladorPost {

    @Autowired
    UsuarioServicePort usuarioServicePort;

    @PostMapping
    public UsuarioOutputDto anaidirUsuario(@RequestBody UsuarioInputDto u) {
        return usuarioServicePort.anaidirUsuario(u);
    }

}
