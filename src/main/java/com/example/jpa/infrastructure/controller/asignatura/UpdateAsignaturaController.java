package com.example.jpa.infrastructure.controller.asignatura;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.asignatura.AsignaturaServicePort;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.asignatura.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.asignatura.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/asignatura")
public class UpdateAsignaturaController {

    @Autowired
    AsignaturaServicePort asignaturaServicePort;

    @PutMapping("{id}")
    public AsignaturaOutputDto updateById(@PathVariable("id") String id, @Valid @RequestBody AsignaturaInputDto asignaturaInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        return asignaturaServicePort.updateById(id,asignaturaInputDto, errors);
    }

}
