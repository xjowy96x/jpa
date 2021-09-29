package com.example.jpa.infrastructure.application.asignatura;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.asignatura.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.asignatura.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import org.springframework.validation.Errors;

import java.util.List;


public interface AsignaturaServicePort {

    public List<AsignaturaOutputDto> getAll();
    public AsignaturaOutputDto getById(String id);
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto u, Errors errors) throws BeanUnprocesableException;
    public AsignaturaOutputDto updateById(String id, AsignaturaInputDto profesorInputDto, Errors errors) throws BeanNotFoundException,BeanUnprocesableException;
    public void deleteAsignaturaById(String id) throws BeanNotFoundException;

}
