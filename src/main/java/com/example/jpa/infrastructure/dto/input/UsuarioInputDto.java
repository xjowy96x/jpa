package com.example.jpa.infrastructure.dto.input;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.util.Date;

@Data

public class UsuarioInputDto {

    @Column(nullable = false,length = 50)
    @Length(min = 6,max = 10, message = "The field must be at least 6 characters and at less 50")
    String user;
    @Column(nullable = false)
    String password;
    @Column(nullable = false)
    String name;
    String surname;
    @Column(nullable = false)
    @Email(message = "No valido")
    String company_email;
    @Column(nullable = false)
    @Email(message = "No valido")
    String personal_email;
    @Column(nullable = false)
    String city;
    @Column(nullable = false)
    Boolean active;
    @Column(nullable = false)
    Date created_date;

}
