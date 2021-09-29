package com.example.jpa.infrastructure.controller.student;

import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.output.StudentOutputDto;

import com.example.jpa.infrastructure.application.port.StudentServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class InsertStudentController {

    @Autowired
    StudentServicePort studentServicePort;

    @PostMapping
    public StudentOutputDto addStudent(@RequestBody StudentInputDto u, Errors errors) throws BeanUnprocesableException {
        return studentServicePort.addStudent(u, errors);
    }

    @PostMapping("addprofesor/{id_student}")
    public StudentOutputDto addStudentProfesor(@PathVariable("id_student") String id_student, @RequestBody StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException {
        return studentServicePort.addStudentProfesor(id_student, studentInputDto, errors);
    }

    @PostMapping("addasignatura/{id_student}")
    public StudentOutputDto addAsignatura(@PathVariable("id_student")String id_student,@RequestBody StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException {
        return studentServicePort.addAsignatura(id_student, studentInputDto, errors);
    }

}
