package com.example.jpa.infrastructure.controller.usuario;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UpdateUsuarioController {

    @Autowired
    UsuarioServicePort usuarioServicePort;

    @PutMapping("{id}")
    public UsuarioOutputDto updateById(@PathVariable("id") Integer id,@Valid @RequestBody UsuarioInputDto u, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        return usuarioServicePort.updateById(id,u, errors);
    }

}
