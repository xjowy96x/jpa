package com.example.jpa.infrastructure.controller.asignatura;

import com.example.jpa.infrastructure.application.asignatura.AsignaturaServicePort;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.asignatura.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
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
