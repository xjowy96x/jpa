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
        if(uin == null) {
            return ;
        }
        if (uin.getUser()!=null)  this.user = uin.getUser();
        if (uin.getPassword()!=null) this.password = uin.getPassword();
        if (uin.getName()!=null) this.name = uin.getName();
        if (uin.getSurname()!=null) this.surname = uin.getSurname();
        if (uin.getCompany_email()!=null) this.company_email = uin.getCompany_email();
        if (uin.getPersonal_email()!=null) this.personal_email = uin.getPersonal_email();
        if (uin.getCity()!=null) this.city = uin.getCity();
        if (uin.getActive()!=null) this.active = uin.getActive();
        if (uin.getCreated_date()!=null) this.created_date = uin.getCreated_date();

    }

}
