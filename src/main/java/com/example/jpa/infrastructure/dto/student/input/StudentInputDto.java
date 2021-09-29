package com.example.jpa.infrastructure.dto.student.input;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Usuario;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data

public class StudentInputDto {


    int id_usuario;
    int num_hours_week;
    String coments;
    String id_profesor;
    String branch;
    List<String> asignaturaList;

}
