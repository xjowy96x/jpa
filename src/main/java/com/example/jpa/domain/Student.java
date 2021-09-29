package com.example.jpa.domain;

import com.example.jpa.infrastructure.dto.input.StudentInputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "student")/*(
        name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "",columnNames = )
        }
)*/
@Data
@NoArgsConstructor
public class Student {

    /*@Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id_student",
            updatable = false
    )*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id_student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario")
    Usuario usuario;
    @Column(nullable = false)
    int num_hours_week;
    String coments;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesor")
    Profesor id_profesor;

    @Column(nullable = false)
    String branch;

    @ManyToMany()
    @JoinTable(name="estudiante_asignatura",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_asignatura"))
    private List<Asignatura> asignatura;


    public Student(StudentInputDto uin) {
        this.modificarStudent(uin);

    }

    public void modificarStudent(StudentInputDto uin) {
        if(uin == null) {
            return ;
        }
        this.usuario = null;
        if (uin.getBranch()!=null) this.branch = uin.getBranch();
        if (uin.getComents()!=null) this.coments = uin.getComents();
        this.num_hours_week = uin.getNum_hours_week();
        this.id_profesor = null;
        this.asignatura = new ArrayList<>();
    }


    public Student(StudentInputDto uin, Usuario usuario, Profesor profesor, List<Asignatura> asignaturaList) {
        this.modificarStudent(uin, usuario, profesor, asignaturaList);

    }

    public void modificarStudent(StudentInputDto uin, Usuario usuarioInputDto, Profesor profesoraInputDto, List<Asignatura> asignaturaList) {
        if(uin == null) {
            return ;
        }
        if (usuarioInputDto!=null)  this.usuario = usuarioInputDto;
        if (uin.getBranch()!=null) this.branch = uin.getBranch();
        if (uin.getComents()!=null) this.coments = uin.getComents();
        this.num_hours_week = uin.getNum_hours_week();
        if (profesoraInputDto!=null)  this.id_profesor = profesoraInputDto;
        if (asignaturaList!=null)  this.asignatura = asignaturaList;
    }


//    public Student(StudentInputDto uin, UsuarioInputDto usuarioInputDto) {
//        this.modificarStudent(uin, usuarioInputDto);
//
//    }
//
//    public void modificarStudent(StudentInputDto uin, UsuarioInputDto usuarioInputDto) {
//        if(uin == null) {
//            return ;
//        }
//        if (usuarioInputDto!=null)  this.id_usuario = new Usuario(usuarioInputDto);
//        if (uin.getBranch()!=null) this.branch = uin.getBranch();
//        if (uin.getComents()!=null) this.coments = uin.getComents();
//        this.num_hours_week = uin.getNum_hours_week();
//    }


}
