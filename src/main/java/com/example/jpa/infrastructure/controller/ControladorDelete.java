package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorDelete {

    @Autowired
    UsuarioService usuarioService;

    @DeleteMapping("{id}")
    public void deleteUsuarioById(@PathVariable("id") Integer id) {
        usuarioService.deleteUsuarioById(id);
    }

}
