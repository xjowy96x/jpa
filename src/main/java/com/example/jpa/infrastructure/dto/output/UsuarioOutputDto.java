package com.example.jpa.infrastructure.dto.output;

import com.example.jpa.domain.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
        this.id_persona = u.getId_persona();
        this.user = u.getUser();
        this.password = u.getPassword();
        this.name = u.getName();
        this.surname = u.getSurname();
        this.company_email = u.getCompany_email();
        this.personal_email = u.getPersonal_email();
        this.city = u.getCity();
        this.active = u.getActive();
        this.created_date = u.getCreated_date();
    }

}
