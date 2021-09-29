package com.example.jpa.infrastructure.controller.profesor;

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

import java.util.List;

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
