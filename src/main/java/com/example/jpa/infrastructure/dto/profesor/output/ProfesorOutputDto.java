package com.example.jpa.infrastructure.dto.profesor.output;

import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProfesorOutputDto {

    String id_profesor;
    Usuario id_usuario;
    String coments;
    String branch;
    List<Student> id_student = new ArrayList<>();



    public ProfesorOutputDto(Profesor u) {
        this.id_profesor = u.getId_profesor();
        if (u.getUsuario()!=null)  this.id_usuario = u.getUsuario();
        if (u.getComents()!=null) this.coments = u.getComents();
        if (u.getBranch()!=null) this.branch = u.getBranch();
        id_student = u.getId_student();


    }

}
