package com.example.jpa.infrastructure.dto.asignatura.input;

import com.example.jpa.domain.Student;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Data

public class AsignaturaInputDto {

    List<String> studentList;
    String coments;
    Date initial_date;
    Date finish_date;

}
