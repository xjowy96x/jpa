package com.example.jpa.infrastructure.controller.profesor;

import com.example.jpa.infrastructure.application.port.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.output.ProfesorOutputDto;

import org.springframework.beans.factory.annotation.Autowired;
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
