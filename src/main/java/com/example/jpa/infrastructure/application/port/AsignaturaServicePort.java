package com.example.jpa.infrastructure.application.port;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.output.AsignaturaOutputDto;
import javassist.NotFoundException;
import org.springframework.validation.Errors;

import java.util.List;


public interface AsignaturaServicePort {

    public List<AsignaturaOutputDto> getAll();
    public AsignaturaOutputDto getById(String id);
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto u, Errors errors) throws BeanUnprocesableException;
    public AsignaturaOutputDto updateById(String id, AsignaturaInputDto profesorInputDto, Errors errors) throws BeanNotFoundException,BeanUnprocesableException;
    public void deleteAsignaturaById(String id) throws BeanNotFoundException, NotFoundException;

}
