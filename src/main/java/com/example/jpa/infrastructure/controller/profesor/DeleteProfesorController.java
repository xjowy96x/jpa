package com.example.jpa.infrastructure.controller.profesor;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.infrastructure.application.port.ProfesorServicePort;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profesor")
public class DeleteProfesorController {

    @Autowired
    ProfesorServicePort profesorServicePort;

    @DeleteMapping("{id}")
    public void deleteProfesorById(@PathVariable("id") String id) throws BeanNotFoundException, NotFoundException {
        profesorServicePort.deleteProfesorById(id);
    }

}
