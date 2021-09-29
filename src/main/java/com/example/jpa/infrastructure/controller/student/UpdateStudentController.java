package com.example.jpa.infrastructure.controller.student;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.application.student.StudentServicePort;
import com.example.jpa.infrastructure.application.usuario.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.dto.student.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import com.example.jpa.infrastructure.dto.usuario.input.UsuarioInputDto;
import com.example.jpa.infrastructure.dto.usuario.output.UsuarioOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class UpdateStudentController {

    @Autowired
    StudentServicePort studentServicePort;

    @PutMapping("{id}")
    public StudentOutputDto updateById(@PathVariable("id") String id, @Valid @RequestBody StudentInputDto studentInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        return studentServicePort.updateById(id,studentInputDto, errors);
    }

}
