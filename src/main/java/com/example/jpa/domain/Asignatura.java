package com.example.jpa.domain;

import com.example.jpa.infrastructure.dto.input.AsignaturaInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity(name = "asignatura")
@Data
@NoArgsConstructor
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id_asignatura;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="estudiante_asignatura",
            joinColumns = @JoinColumn(name = "id_asignatura"),
            inverseJoinColumns = @JoinColumn(name = "id_student"))
    private List<Student> student;

    String comments;
    @Column(nullable = false)
    Date initial_date;
    Date finish_date;

    public Asignatura(AsignaturaInputDto asignaturaInputDto, List<Student> student) {
        this.modificarAsignatura(asignaturaInputDto, student);
    }

    public void modificarAsignatura(AsignaturaInputDto asignaturaInputDto, List<Student> studentList) {
        if(asignaturaInputDto == null) {
            return ;
        }
        if (asignaturaInputDto.getComents()!=null)  this.comments = asignaturaInputDto.getComents();
        if (asignaturaInputDto.getFinish_date()!=null) this.finish_date = asignaturaInputDto.getFinish_date();
        if (asignaturaInputDto.getInitial_date()!=null) this.initial_date = asignaturaInputDto.getInitial_date();
        if (asignaturaInputDto.getStudentList()!=null) this.student = studentList;

    }

    public Asignatura(AsignaturaInputDto asignaturaInputDto) {
        this.modificarAsignaturaSinLista(asignaturaInputDto);
    }

    public void modificarAsignaturaSinLista(AsignaturaInputDto asignaturaInputDto) {
        if(asignaturaInputDto == null) {
            return ;
        }
        if (asignaturaInputDto.getComents()!=null)  this.comments = asignaturaInputDto.getComents();
        if (asignaturaInputDto.getFinish_date()!=null) this.finish_date = asignaturaInputDto.getFinish_date();
        if (asignaturaInputDto.getInitial_date()!=null) this.initial_date = asignaturaInputDto.getInitial_date();
        if (asignaturaInputDto.getStudentList()!=null) this.student = new ArrayList<>();

    }

}
