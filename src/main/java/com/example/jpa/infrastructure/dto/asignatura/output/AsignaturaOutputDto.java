package com.example.jpa.infrastructure.dto.asignatura.output;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AsignaturaOutputDto {

    String id_asignatura;
    List<Student> studentList;
    String coments;
    Date initial_date;
    Date finish_date;



    public AsignaturaOutputDto(Asignatura a) {
        this.id_asignatura = a.getId_asignatura();
        if (a.getFinish_date()!=null)  this.finish_date = a.getFinish_date();
        if (a.getInitial_date()!=null) this.initial_date = a.getInitial_date();
        if (a.getComents()!=null) this.coments = a.getComents();
        if (a.getStudentList()!=null) this.studentList = a.getStudentList();

    }

}
