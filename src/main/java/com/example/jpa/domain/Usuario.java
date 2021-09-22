package com.example.jpa.domain;

import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_persona;

    @Column(nullable = false,length = 50)
    @Length(min = 6,max = 10, message = "The field must be at least 6 characters and at less 50")
    String user;
    //@NotNull --> no deja meter valores al hacer insert nulos, pero no creo la columna con not null
    @Column(nullable = false)
    String password;
    //@NotNull
    @Column(nullable = false)
    String name;

    String surname;
    //@NotNull
    @Column(nullable = false)
    @Email(message = "No valido")
    String company_email;
    //@NotNull
    @Column(nullable = false)
    @Email(message = "No valido")
    String personal_email;
    //@NotNull
    @Column(nullable = false)
    String city;
    //@NotNull
    @Column(nullable = false)
    Boolean active;
    //@NotNull
    @Column(nullable = false)
    Date created_date;

    public Usuario(UsuarioInputDto uin) {
        this.user = uin.getUser();
        this.password = uin.getPassword();
        this.name = uin.getName();
        this.surname = uin.getSurname();
        this.company_email = uin.getCompany_email();
        this.personal_email = uin.getPersonal_email();
        this.city = uin.getCity();
        this.active = uin.getActive();
        this.created_date = uin.getCreated_date();

    }

}
