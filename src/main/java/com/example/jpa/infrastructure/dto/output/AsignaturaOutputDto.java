package com.example.jpa.infrastructure.dto.output;

import com.example.jpa.domain.Asignatura;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AsignaturaOutputDto {

    String id_asignatura;
    String coments;
    Date initial_date;
    Date finish_date;
    List<String> studentList;



    public AsignaturaOutputDto(Asignatura a) {
        this.id_asignatura = a.getId_asignatura();
        if (a.getFinish_date()!=null)  this.finish_date = a.getFinish_date();
        if (a.getInitial_date()!=null) this.initial_date = a.getInitial_date();
        if (a.getComments()!=null) this.coments = a.getComments();
        if (a.getStudent()!=null) this.studentList = a.getStudent().stream().map( p-> p.getId_student()).collect(Collectors.toList());

    }

}
