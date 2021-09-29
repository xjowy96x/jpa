package com.example.jpa.infrastructure.controller.profesor;

import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.output.ProfesorOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profesor")
public class InsertProfesorController {

    @Autowired
    ProfesorServicePort profesorServicePort;

    @PostMapping
    public ProfesorOutputDto addProfesor(@RequestBody ProfesorInputDto u, Errors errors) throws BeanUnprocesableException {
        return profesorServicePort.addProfesor(u, errors);
    }



}
