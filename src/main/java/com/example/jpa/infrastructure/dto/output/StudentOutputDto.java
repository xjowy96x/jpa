package com.example.jpa.infrastructure.dto.output;

import com.example.jpa.domain.Student;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentOutputDto {

    String id_student;
    int id_usuario;
    int num_hours_week;
    String coments;
    String id_profesor;
    String branch;
    List<String> asignaturaList;


    public StudentOutputDto(Student u) {
        this.id_student = u.getId_student();
        if (u.getUsuario()!=null)  this.id_usuario = u.getUsuario().getId_user();
        this.num_hours_week = u.getNum_hours_week();
        if (u.getComents()!=null) this.coments = u.getComents();
        if (u.getId_profesor()!=null) this.id_profesor = u.getId_profesor().getId_profesor();
        if (u.getBranch()!=null) this.branch = u.getBranch();
        if (u.getAsignatura()!=null) this.asignaturaList = u.getAsignatura().stream().map( p-> p.getId_asignatura()).collect(Collectors.toList());

    }

}
