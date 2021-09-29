package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Student;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.port.AsignaturaServicePort;
import com.example.jpa.infrastructure.dto.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.output.AsignaturaOutputDto;
import com.example.jpa.infrastructure.repository.AsignaturaRepository;
import com.example.jpa.infrastructure.repository.ProfesorRepository;
import com.example.jpa.infrastructure.repository.StudentRepository;
import com.example.jpa.infrastructure.repository.UsuarioRepositorio;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsignaturaService implements AsignaturaServicePort {

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
                List<Asignatura> asignaturaList = student.getAsignatura();
                asignaturaList.add(a);
                student.setAsignatura(asignaturaList);
                studentList.add(student);
            }
        }
        a.setStudent(studentList);
        asignaturaRepository.saveAndFlush(a);
        return new AsignaturaOutputDto(a);
    }




    public AsignaturaOutputDto updateById(String id, AsignaturaInputDto asignaturaInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Asignatura a = asignaturaRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado"));;
        List<Student> studentList = new ArrayList<>();
        if(asignaturaInputDto.getStudentList() != null) {
            for(String s : asignaturaInputDto.getStudentList()) {
                Student student = studentRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + s + " no encontrado"));
                List<Asignatura> asignaturaList = student.getAsignatura();
                asignaturaList.add(a);
                student.setAsignatura(asignaturaList);
                studentList.add(student);
            }
        }
        a.modificarAsignatura(asignaturaInputDto,studentList);
        asignaturaRepository.saveAndFlush(a);
        return new AsignaturaOutputDto(a);
    }


    public void deleteAsignaturaById(String id) throws BeanNotFoundException,NotFoundException{
        Asignatura asignatura = asignaturaRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado"));
        List<Student> studentList = asignatura.getStudent();
        if(studentList != null) {
            throw new NotFoundException("La asignatura tiene estudiantes, no puede ser borrada.");
        } else {
            asignaturaRepository.delete(asignaturaRepository.findById(id).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado")));
        }
    }

}
