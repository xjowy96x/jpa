package com.example.jpa.infrastructure.controller.asignatura;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.infrastructure.application.port.AsignaturaServicePort;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asignatura")
public class DeleteAsignaturaController {

    @Autowired
    AsignaturaServicePort asignaturaServicePort;

    @DeleteMapping("{id}")
    public void deleteAsignaturaById(@PathVariable("id") String id) throws BeanNotFoundException, NotFoundException {
        asignaturaServicePort.deleteAsignaturaById(id);
    }

}
