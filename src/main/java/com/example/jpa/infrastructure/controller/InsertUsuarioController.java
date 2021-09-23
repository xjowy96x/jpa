package com.example.jpa.infrastructure.controller;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.application.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class InsertUsuarioController {

    @Autowired
    UsuarioServicePort usuarioServicePort;

    @PostMapping
    public UsuarioOutputDto addUsuario(@Valid @RequestBody UsuarioInputDto u, Errors errors) throws BeanUnprocesableException {
        return usuarioServicePort.addUsuario(u, errors);
    }

}
