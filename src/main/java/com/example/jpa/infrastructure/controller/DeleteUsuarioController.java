package com.example.jpa.infrastructure.controller;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.application.UsuarioServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class DeleteUsuarioController {

    @Autowired
    UsuarioServicePort usuarioServicePort;

    @DeleteMapping("{id}")
    public void deleteUsuarioById(@PathVariable("id") Integer id) throws BeanNotFoundException {
        usuarioServicePort.deleteUsuarioById(id);
    }

}
