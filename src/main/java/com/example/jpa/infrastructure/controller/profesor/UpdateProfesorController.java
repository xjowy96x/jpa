package com.example.jpa.infrastructure.controller.profesor;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.application.student.StudentServicePort;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.dto.student.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profesor")
public class UpdateProfesorController {

    @Autowired
    ProfesorServicePort profesorServicePort;

    @PutMapping("{id}")
    public ProfesorOutputDto updateById(@PathVariable("id") String id, @Valid @RequestBody ProfesorInputDto profesorInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        return profesorServicePort.updateById(id,profesorInputDto, errors);
    }

}
