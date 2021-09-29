package com.example.jpa.infrastructure.dto.usuario.output;

import com.example.jpa.domain.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class UsuarioOutputDto {

    int id_persona;
    String user;
    String password;
    String name;
    String surname;
    String company_email;
    String personal_email;
    String city;
    Boolean active;
    Date created_date;


    public UsuarioOutputDto(Usuario u) {
        this.id_persona = u.getId_user();
        if (u.getUser()!=null)  this.user = u.getUser();
        if (u.getPassword()!=null) this.password = u.getPassword();
        if (u.getName()!=null) this.name = u.getName();
        if (u.getSurname()!=null) this.surname = u.getSurname();
        if (u.getCompany_email()!=null) this.company_email = u.getCompany_email();
        if (u.getPersonal_email()!=null) this.personal_email = u.getPersonal_email();
        if (u.getCity()!=null) this.city = u.getCity();
        if (u.getActive()!=null) this.active = u.getActive();
        if (u.getCreated_date()!=null) this.created_date = u.getCreated_date();


    }

}
