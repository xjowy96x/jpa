package com.example.jpa.infrastructure.application.asignatura;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.profesor.ProfesorServicePort;
import com.example.jpa.infrastructure.dto.asignatura.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.asignatura.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import com.example.jpa.infrastructure.dto.profesor.output.ProfesorOutputDto;
import com.example.jpa.infrastructure.repository.AsignaturaRepository;
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
public class AsignaturaService implements AsignaturaServicePort {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;

    public List<AsignaturaOutputDto> getAll() {
        return asignaturaRepository.findAll().stream().map(p -> new AsignaturaOutputDto(p)).collect(Collectors.toList());
    }


    public AsignaturaOutputDto getById(String id) {

        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado"));

        return new AsignaturaOutputDto(asignatura);
    }


    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto asignaturaInputDto, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Asignatura a = new Asignatura(asignaturaInputDto);
        List<Student> studentList = new ArrayList<>();
        if(asignaturaInputDto.getStudentList() != null) {
            for(String s : asignaturaInputDto.getStudentList()) {
                Student student = studentRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + s + " no encontrado"));
                List<Asignatura> asignaturaList = student.getAsignaturaList();
                asignaturaList.add(a);
                student.setAsignaturaList(asignaturaList);
                studentList.add(student);
                studentRepository.saveAndFlush(student);
            }
        }
        a.setStudentList(studentList);
        asignaturaRepository.saveAndFlush(a);
        return new AsignaturaOutputDto(a);
    }




    public AsignaturaOutputDto updateById(String id, AsignaturaInputDto profesorInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
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


    public void deleteAsignaturaById(String id) throws BeanNotFoundException{
        asignaturaRepository.delete(asignaturaRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado")));
    }

}
