package com.example.jpa.infrastructure.application.student;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Usuario;
import com.example.jpa.exception.BeanNotFoundException;
import com.example.jpa.exception.BeanUnprocesableException;
import com.example.jpa.infrastructure.application.usuario.UsuarioServicePort;
import com.example.jpa.infrastructure.dto.usuario.input.UsuarioInputDto;
import com.example.jpa.domain.Student;
import com.example.jpa.infrastructure.dto.student.input.StudentInputDto;
import com.example.jpa.infrastructure.dto.student.output.StudentOutputDto;
import com.example.jpa.infrastructure.dto.usuario.output.UsuarioOutputDto;
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

        Profesor profesor = profesorRepository.getUsuario(usuario);

        if(profesor == null) {
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

//            Student student = new Student(u, usuario, profesor, asignaturaList);
            Student student = new Student(u);
            student.setUsuario(usuario);
            student.setId_profesor(profesor);
            student.setAsignaturaList(asignaturaList);

            studentRepositorio.saveAndFlush(student);

            for(Asignatura asignatura : asignaturaList) {
                List<Student> studentList = asignatura.getStudentList();
                studentList.add(student);
                asignatura.setStudentList(studentList);
                asignaturaRepository.saveAndFlush(asignatura);
            }

            StudentOutputDto studentOutputDto = new StudentOutputDto(student);
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

        List<Asignatura> asignaturaList = new ArrayList<>();
        if(studentInputDto.getAsignaturaList() != null) {
            for (String s : studentInputDto.getAsignaturaList()) {
                asignaturaList.add(asignaturaRepository.findById(s).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado")));
            }
        }
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado"));

        List<Asignatura> asignaturaList1 = student.getAsignaturaList();

        for(Asignatura asignatura : asignaturaList) {
            if(!asignaturaList1.contains(asignatura)) {
                asignaturaList1.add(asignatura);
            }
        }

        student.setAsignaturaList(asignaturaList1);

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

        List<Asignatura> asignaturaList1 = student.getAsignaturaList();

        asignaturaList1.removeAll(asignaturaList);

        student.setAsignaturaList(asignaturaList1);

        studentRepositorio.saveAndFlush(student);

        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }



    public StudentOutputDto updateById(String id, StudentInputDto u, Errors errors) throws BeanNotFoundException, BeanUnprocesableException {
        if(errors.hasErrors()) {
            throw new BeanUnprocesableException(errors.getFieldError().toString());
        }
        Student student = studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        Usuario usuario = usuarioRepositorio.findById(u.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
        Profesor profesor = profesorRepository.findById(u.getId_profesor()).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
//        if(student != null) {
//            if(u.getId_usuario() != student.getId_usuario().getId_user()) {
//                usuario = usuarioRepositorio.findById(u.getId_usuario()).orElseThrow(() -> new BeanNotFoundException("Usuario: con id= " + id + " no encontrado"));
//            }
//            if(!u.getId_profesor().equals(student.getId_profesor().getId_profesor())) {
//                profesor = profesorRepository.findById(u.getId_profesor()).orElseThrow(() -> new BeanNotFoundException("Profesor: con id= " + id + " no encontrado"));
//            }
//            student.modificarStudent(u,usuario,profesor);
//        }
        student.modificarStudent(u,usuario,profesor,null);
        studentRepositorio.saveAndFlush(student);

        StudentOutputDto studentOutputDto = new StudentOutputDto(student);
        return studentOutputDto;
    }



    public void deleteStudentById(String id) throws BeanNotFoundException{
        studentRepositorio.delete(studentRepositorio.findById(id).orElseThrow(() -> new BeanNotFoundException("Student: con id= " + id + " no encontrado")));
    }

}
