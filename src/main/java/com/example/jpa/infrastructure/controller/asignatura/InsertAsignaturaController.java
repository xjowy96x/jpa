package com.example.jpa.infrastructure.controller.asignatura;

import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.asignatura.AsignaturaServicePort;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.asignatura.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.asignatura.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asignatura")
public class InsertAsignaturaController {

    @Autowired
    AsignaturaServicePort asignaturaServicePort;

    @PostMapping
    public AsignaturaOutputDto addAsignatura(@RequestBody AsignaturaInputDto asignaturaInputDto, Errors errors) throws BeanUnprocesableException {
        return asignaturaServicePort.addAsignatura(asignaturaInputDto, errors);
    }



}
