package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.domain.Student;
import com.example.jpa.infrastructure.application.port.StudentServicePort;
import com.example.jpa.infrastructure.dto.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.output.StudentOutputDto;
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
public class StudentService implements StudentServicePort {

    @Autowired
    StudentRepository studentRepositorio;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    AsignaturaRepository asignaturaRepository;

    public List<StudentOutputDto> getAll() {
        return studentRepositorio.findAll().stream().map(p -> new StudentOutputDto(p)).collect(Collectors.toList());
    }


    public StudentOutputDto getById(String id,String output) {
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        return new StudentOutputDto(student);
    }


    public StudentOutputDto addStudent(StudentInputDto u, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Usuario usuario = usuarioRepositorio.findById(u.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + u.getId_usuario()+1 + " no encontrado"));


        Student student = studentRepositorio.getUsuario(usuario);

        Profesor profesor = profesorRepository.getUsuario(usuario);

        if(student == null && profesor == null) {
            if (u.getId_profesor() != null) {
                profesor = profesorRepository.findById(u.getId_profesor()).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + u.getId_profesor() + " no encontrado"));
            }
            List<Asignatura> asignaturaList = new ArrayList<>();
            if (u.getAsignaturaList() != null) {
                for (String s : u.getAsignaturaList()) {
                    Asignatura asignatura = asignaturaRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + s + " no encontrado"));
                    asignaturaList.add(asignatura);
                }
            }

            Student student1 = new Student(u, usuario, profesor, asignaturaList);

            studentRepositorio.saveAndFlush(student1);

            for(Asignatura asignatura : asignaturaList) {
                List<Student> studentList = asignatura.getStudent();
                studentList.add(student1);
                asignatura.setStudent(studentList);
                asignaturaRepository.saveAndFlush(asignatura);
            }

            StudentOutputDto studentOutputDto = new StudentOutputDto(student1);
            return studentOutputDto;
        }
        return null;

    }

    public StudentOutputDto addStudentProfesor(String id_student, StudentInputDto studentInputDto, Errors errors) throws BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Profesor profesor = profesorRepository.findById(studentInputDto.getId_profesor()).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + studentInputDto.getId_profesor() + " no encontrado"));
        Student student = studentRepositorio.findById(id_student).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id_student + " no encontrado"));
        student.setId_profesor(profesor);
        studentRepositorio.saveAndFlush(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }

    public StudentOutputDto addAsignatura(String id, StudentInputDto studentInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado"));
        List<Asignatura> asignaturaList = new ArrayList<>();
        if(studentInputDto.getAsignaturaList() != null) {
            for (String s : studentInputDto.getAsignaturaList()) {
                Asignatura asignatura = asignaturaRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado"));
                List<Student> studentList = asignatura.getStudent();
                if(!studentList.contains(student)) {
                    studentList.add(student);
                }

                asignatura.setStudent(studentList);
                asignaturaRepository.saveAndFlush(asignatura);
                asignaturaList.add(asignatura);
            }
        }
        List<Asignatura> asignaturaList1 = student.getAsignatura();

        for(Asignatura asignatura : asignaturaList) {
            if(!asignaturaList1.contains(asignatura)) {
                asignaturaList1.add(asignatura);
            }
        }
        student.setAsignatura(asignaturaList1);
        studentRepositorio.saveAndFlush(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }

    public StudentOutputDto deleteAsignatura(String id, StudentInputDto studentInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }

        List<Asignatura> asignaturaList = new ArrayList<>();
        if(studentInputDto.getAsignaturaList() != null) {
            for (String s : studentInputDto.getAsignaturaList()) {
                asignaturaList.add(asignaturaRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado")));
            }
        }
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado"));
        List<Asignatura> asignaturaList1 = student.getAsignatura();
        asignaturaList1.removeAll(asignaturaList);
        student.setAsignatura(asignaturaList1);
        studentRepositorio.saveAndFlush(student);
        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }



    public StudentOutputDto updateById(String id, StudentInputDto studentInputDto, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        Usuario usuario = usuarioRepositorio.findById(studentInputDto.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        Profesor profesor = profesorRepository.findById(studentInputDto.getId_profesor()).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));

        List<Asignatura> asignaturaList = new ArrayList<>();
        if(studentInputDto.getAsignaturaList() != null) {
            for (String s : studentInputDto.getAsignaturaList()) {
                Asignatura asignatura = asignaturaRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Asignatura: con id= " + id + " no encontrado"));
                List<Student> studentList = asignatura.getStudent();
                studentList.add(student);
                asignatura.setStudent(studentList);
                asignaturaRepository.saveAndFlush(asignatura);
                asignaturaList.add(asignatura);
            }
        }
        List<Asignatura> asignaturaList1 = student.getAsignatura();

        for(Asignatura asignatura : asignaturaList) {
            if(!asignaturaList1.contains(asignatura)) {
                asignaturaList1.add(asignatura);
            }
        }
        student.setAsignatura(asignaturaList1);
        studentRepositorio.saveAndFlush(student);
        student.modificarStudent(studentInputDto,usuario,profesor,asignaturaList1);
        studentRepositorio.saveAndFlush(student);

        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }



    public void deleteStudentById(String id) throws BeanNotFoundException,NotFoundException{

        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado"));
        Profesor profesor = student.getId_profesor();
        List<Asignatura> asignaturaList = student.getAsignatura();

        if(asignaturaList != null) {
            throw new NotFoundException("El alumno tiene asignaturas, no puede ser borrado");
        } else {
            if(profesor != null) {
                throw new NotFoundException("El alumno tiene profesor, no puede ser borrado");
            }
            studentRepositorio.delete(student);
        }

    }

}
