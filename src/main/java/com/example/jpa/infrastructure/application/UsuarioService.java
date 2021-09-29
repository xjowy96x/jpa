package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.UsuarioServicePort;
import com.example.jpa.infrastructure.repository.ProfesorRepository;
import com.example.jpa.infrastructure.repository.StudentRepository;
import com.example.jpa.infrastructure.repository.UsuarioRepositorio;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioServicePort {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ProfesorRepository profesorRepository;

    @Autowired
    StudentRepository studentRepository;

    public List<UsuarioOutputDto> getAll() {

        return usuarioRepositorio.findAll().stream().map(p -> new UsuarioOutputDto(p)).collect(Collectors.toList());

    }


    public UsuarioOutputDto getById(Integer id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public List<UsuarioOutputDto> getByName(String name){

        return usuarioRepositorio.getByName(name).stream().map(p -> new UsuarioOutputDto(p)).collect(Collectors.toList());

    }


    public UsuarioOutputDto addUsuario(UsuarioInputDto u, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Usuario usuario = new Usuario(u);
        usuarioRepositorio.saveAndFlush(usuario);
        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public UsuarioOutputDto updateById(Integer id, UsuarioInputDto u, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        if(usuario != null) {
            usuario.modificarUsuario(u);
        }
        usuarioRepositorio.saveAndFlush(usuario);

        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public void deleteUsuarioById(Integer id) throws BeanNotFoundException,NotFoundException{
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));

        Student student = studentRepository.getUsuario(usuario);

        Profesor profesor = profesorRepository.getUsuario(usuario);
        if(student != null || profesor != null) {
            throw new NotFoundException("La asignatura tiene algun estudiante matriculado. No puede ser borrada.");
        } else {
            usuarioRepositorio.delete(usuarioRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado")));
        }
    }

}
