package com.example.jpa.domain;

import com.example.jpa.infrastructure.dto.profesor.input.ProfesorInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profesor")
@Data
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id_profesor;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario")
    Usuario usuario;
    String coments;
    @Column(nullable = false)
    String branch;
    @OneToMany(mappedBy = "id_profesor",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    List<Student> id_student = new ArrayList<>();


    public Profesor(ProfesorInputDto uin) {
        this.modificarProfesor(uin);

    }

    public void modificarProfesor(ProfesorInputDto uin) {
        if(uin == null) {
            return ;
        }
        this.usuario = null;
        if (uin.getBranch()!=null) this.branch = uin.getBranch();
        if (uin.getComents()!=null) this.coments = uin.getComents();

    }


    public Profesor(ProfesorInputDto uin, Usuario usuario) {
        this.modificarProfesor(uin, usuario);

    }

    public void modificarProfesor(ProfesorInputDto uin, Usuario usuarioInputDto) {
        if(uin == null) {
            return ;
        }
        if (usuarioInputDto!=null)  this.usuario = usuarioInputDto;
        if (uin.getBranch()!=null) this.branch = uin.getBranch();
        if (uin.getComents()!=null) this.coments = uin.getComents();

    }

    public Profesor(ProfesorInputDto uin, Usuario usuario, List<Student> studentList) {
        this.modificarProfesor(uin, usuario, studentList);

    }

    public void modificarProfesor(ProfesorInputDto uin, Usuario usuarioInputDto, List<Student> studentList) {
        if(uin == null) {
            return ;
        }
        if (usuarioInputDto!=null)  this.usuario = usuarioInputDto;
        if (uin.getBranch()!=null) this.branch = uin.getBranch();
        if (uin.getComents()!=null) this.coments = uin.getComents();
        if(uin.getId_student()!=null) this.id_student = studentList;
    }

}
