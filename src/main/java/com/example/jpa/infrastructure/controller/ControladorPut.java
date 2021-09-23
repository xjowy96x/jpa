package com.example.jpa.infrastructure.controller;

import com.example.jpa.infrastructure.application.UsuarioService;
import com.example.jpa.infrastructure.application.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class ControladorPut {

    @Autowired
    UsuarioServicePort usuarioServicePort;

    @PutMapping("{id}")
    public UsuarioOutputDto updateById(@PathVariable("id") Integer id, @RequestBody UsuarioInputDto u) throws Exception{
        return usuarioServicePort.updateById(id,u);
    }

}
