package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.repository.ProfesorRepository;
import com.example.jpa.infrastructure.repository.StudentRepository;
import com.example.jpa.infrastructure.repository.UsuarioRepositorio;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

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
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
        return new ProfesorOutputDto(profesor);
    }


    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInputDto, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Usuario usuario = usuarioRepositorio.findById(profesorInputDto.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + profesorInputDto.getId_usuario() + " no encontrado"));

        Student student = studentRepository.getUsuario(usuario);

        Profesor profesor = profesorRepository.getUsuario(usuario);

        if(student == null && profesor == null) {
            Profesor profesor1 = new Profesor(profesorInputDto, usuario);
            profesorRepository.saveAndFlush(profesor1);
            ProfesorOutputDto studentOutputDto = new ProfesorOutputDto(profesor1);
            return studentOutputDto;
        } else {
            return null;
        }
    }


    public ProfesorOutputDto updateById(String id, ProfesorInputDto profesorInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
        Usuario usuario = null;
        if(profesorInputDto.getId_usuario() == 0) {
            usuario = usuarioRepositorio.findById(profesor.getUsuario().getId_user()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + profesorInputDto.getId_usuario() + " no encontrado"));
        } else {
            usuario = usuarioRepositorio.findById(profesorInputDto.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + profesorInputDto.getId_usuario() + " no encontrado"));
        }


        profesor.modificarProfesor(profesorInputDto,usuario);
        profesorRepository.saveAndFlush(profesor);

        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor);
        return profesorOutputDto;
    }


    public void deleteProfesorById(String id) throws BeanNotFoundException,NotFoundException{
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
        List<Student> studentList = profesor.getId_student();

        if(studentList != null) {
            throw new NotFoundException("El profesor tiene estudiantes, no puede ser borrado");
        } else {
            profesorRepository.delete(profesorRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("profesor: con id= " + id + " no encontrado")));
        }
    }

}
