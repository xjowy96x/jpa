package com.example.jpa.infrastructure.controller.profesor;

import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.application.student.StudentServicePort;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesor")
public class FindProfesorController {

    @Autowired
    ProfesorServicePort profesorServicePort;

    @GetMapping
    public List<ProfesorOutputDto> getAll() {
        return profesorServicePort.getAll();
    }

    @GetMapping("{id}")
    public ProfesorOutputDto getById(@PathVariable String id, @RequestParam(value="output", defaultValue = "simple") String output) {
        return profesorServicePort.getById(id, output);
    }

}
