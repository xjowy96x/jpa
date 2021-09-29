package com.example.jpa.infrastructure.controller.student;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.StudentServicePort;
import com.example.jpa.infrastructure.dto.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.output.StudentOutputDto;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class DeleteStudentController {

    @Autowired
    StudentServicePort studentServicePort;

    @DeleteMapping("{id}")
    public void deleteUsuarioById(@PathVariable("id") String id) throws BeanNotFoundException, NotFoundException {
        studentServicePort.deleteStudentById(id);
    }

    @DeleteMapping("deleteasignatura/{id_student}")
    public StudentOutputDto deleteAsignatura(@PathVariable("id_student")String id_student, @RequestBody StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException {
        return studentServicePort.deleteAsignatura(id_student, studentInputDto, errors);
    }

}
