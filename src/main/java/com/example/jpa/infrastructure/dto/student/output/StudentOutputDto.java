package com.example.jpa.infrastructure.dto.student.output;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Usuario;
import com.example.jpa.domain.Student;
import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class StudentOutputDto {

    String id_student;
    Usuario id_usuario;
    int num_hours_week;
    String coments;
    Profesor id_profesor;
    String branch;
    List<Asignatura> asignaturaList;


    public StudentOutputDto(Student u) {
        this.id_student = u.getId_student();
        if (u.getUsuario()!=null)  this.id_usuario = u.getUsuario();
        this.num_hours_week = u.getNum_hours_week();
        if (u.getComents()!=null) this.coments = u.getComents();
        if (u.getId_profesor()!=null) this.id_profesor = u.getId_profesor();
        if (u.getBranch()!=null) this.branch = u.getBranch();
        if (u.getAsignaturaList()!=null) this.asignaturaList = u.getAsignaturaList();


    }

}
