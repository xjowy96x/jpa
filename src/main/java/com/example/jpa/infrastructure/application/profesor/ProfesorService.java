package com.example.jpa.infrastructure.application.profesor;

import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.student.StudentServicePort;
import com.example.jpa.infrastructure.application.usuario.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.dto.student.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import com.example.jpa.infrastructure.repository.ProfesorRepository;
import com.example.jpa.infrastructure.repository.StudentRepository;
import com.example.jpa.infrastructure.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService implements ProfesorServicePort {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    StudentRepository studentRepository;

    public List<ProfesorOutputDto> getAll() {
        return profesorRepository.findAll().stream().map(p -> new ProfesorOutputDto(p)).collect(Collectors.toList());
    }

    public ProfesorOutputDto getById(String id,String output) {
        if(output.equals("simple")) {

        }
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
        return new ProfesorOutputDto(profesor);
    }


    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Usuario usuario = usuarioRepositorio.findById(profesorInputDto.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + profesorInputDto.getId_usuario() + 1 + " no encontrado"));

        Student student = studentRepository.getUsuario(usuario);

        if(student == null) {
//            Profesor profesor = new Profesor(profesorInputDto, usuario);
            Profesor profesor = new Profesor(profesorInputDto);
            profesor.setUsuario(usuario);
            profesorRepository.saveAndFlush(profesor);
            ProfesorOutputDto studentOutputDto = new ProfesorOutputDto(profesor);
            return studentOutputDto;
        } else {
            return null;
        }
    }


    public ProfesorOutputDto updateById(String id, ProfesorInputDto profesorInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
//        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
//        Usuario user = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
//        Student
//
//        modificarProfesor()
//        profesorRepository.saveAndFlush(profesor);
//
//        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
        return null;
    }


    public void deleteProfesorById(String id) throws BeanNotFoundException{
        profesorRepository.delete(profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado")));
    }

}
