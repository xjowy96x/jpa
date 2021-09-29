package com.example.jpa.domain;

import com.example.jpa.infrastructure.dto.asignatura.input.AsignaturaInputDto;
import com.example.jpa.infrastructure.dto.usuario.input.UsuarioInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Table
@Entity(name = "asignatura")
@Data
@NoArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id_asignatura;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> studentList;

    String coments;
    @Column(nullable = false)
    Date initial_date;
    Date finish_date;

    public Asignatura(AsignaturaInputDto asignaturaInputDto, List<Student> studentList) {
        this.modificarAsignatura(asignaturaInputDto, studentList);
    }

    public void modificarAsignatura(AsignaturaInputDto asignaturaInputDto, List<Student> studentList) {
        if(asignaturaInputDto == null) {
            return ;
        }
        if (asignaturaInputDto.getComents()!=null)  this.coments = asignaturaInputDto.getComents();
        if (asignaturaInputDto.getFinish_date()!=null) this.finish_date = asignaturaInputDto.getFinish_date();
        if (asignaturaInputDto.getInitial_date()!=null) this.initial_date = asignaturaInputDto.getInitial_date();
        if (asignaturaInputDto.getStudentList()!=null) this.studentList = studentList;

    }

    public Asignatura(AsignaturaInputDto asignaturaInputDto) {
        this.modificarAsignaturaSinLista(asignaturaInputDto);
    }

    public void modificarAsignaturaSinLista(AsignaturaInputDto asignaturaInputDto) {
        if(asignaturaInputDto == null) {
            return ;
        }
        if (asignaturaInputDto.getComents()!=null)  this.coments = asignaturaInputDto.getComents();
        if (asignaturaInputDto.getFinish_date()!=null) this.finish_date = asignaturaInputDto.getFinish_date();
        if (asignaturaInputDto.getInitial_date()!=null) this.initial_date = asignaturaInputDto.getInitial_date();
        if (asignaturaInputDto.getStudentList()!=null) this.studentList = new ArrayList<>();

    }

}
