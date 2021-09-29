package com.example.jpa.infrastructure.dto.input;

import com.example.jpa.domain.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ProfesorInputDto {


    int id_usuario;
    String coments;
    String branch;
    List<String> id_student;

}
