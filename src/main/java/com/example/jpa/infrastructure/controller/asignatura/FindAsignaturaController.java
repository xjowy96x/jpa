package com.example.jpa.infrastructure.controller.asignatura;

import com.example.jpa.infrastructure.application.port.AsignaturaServicePort;
import com.example.jpa.infrastructure.dto.output.AsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignatura")
public class FindAsignaturaController {

    @Autowired
    AsignaturaServicePort asignaturaServicePort;

    @GetMapping
    public List<AsignaturaOutputDto> getAll() {
        return asignaturaServicePort.getAll();
    }

    @GetMapping("{id}")
    public AsignaturaOutputDto getById(@PathVariable String id) {
        return asignaturaServicePort.getById(id);
    }

}
