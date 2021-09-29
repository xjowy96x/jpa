package com.example.jpa.infrastructure.controller.student;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.StudentServicePort;
import com.example.jpa.infrastructure.dto.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.output.StudentOutputDto;
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
