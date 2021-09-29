package com.example.jpa.infrastructure.application.student;

import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.dto.usuario.input.UsuarioInputDto;
import com.example.jpa.infrastructure.dto.student.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;


public interface StudentServicePort {

    public List<StudentOutputDto> getAll();
    public StudentOutputDto getById(String id, String output);
    public StudentOutputDto addStudent(StudentInputDto u, Errors errors) throws BeanUnprocesableException;
    public StudentOutputDto addStudentProfesor(String id_student, StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException;
    public StudentOutputDto addAsignatura(String id_student, StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException;
    public StudentOutputDto deleteAsignatura(String id_student, StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException;
    public StudentOutputDto updateById(String id, StudentInputDto u, Errors errors) throws BeanNotFoundException,BeanUnprocesableException;
    public void deleteStudentById(String id) throws BeanNotFoundException;

}
