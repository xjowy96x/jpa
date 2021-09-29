package com.example.jpa.infrastructure.application.port;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.output.ProfesorOutputDto;

import javassist.NotFoundException;
import org.springframework.validation.Errors;

import java.util.List;


public interface ProfesorServicePort {

    public List<ProfesorOutputDto> getAll();
    public ProfesorOutputDto getById(String id, String output);
    public ProfesorOutputDto addProfesor(ProfesorInputDto u, Errors errors) throws BeanUnprocesableException;
    public ProfesorOutputDto updateById(String id, ProfesorInputDto profesorInputDto, Errors errors) throws BeanNotFoundException,BeanUnprocesableException;
    public void deleteProfesorById(String id) throws BeanNotFoundException, NotFoundException;

}
