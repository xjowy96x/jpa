package com.example.jpa.infrastructure.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data //Setters y getters
@NoArgsConstructor
public class UsuarioInputDto {

    String user;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;

}
