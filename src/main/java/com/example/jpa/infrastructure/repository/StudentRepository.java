package com.example.jpa.infrastructure.repository;

import com.example.jpa.domain.Profesor;
import com.example.jpa.domain.Student;
import com.example.jpa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

//    @Query("select count(s.*) from Student e join Usuario u on e.id_user=u.id_user where e.id_user = ?1")
//    int getIdExiste(int id_user);

//    List<Student> findByUsuario(Usuario primaryKey);

    @Query("SELECT s FROM Student s where s.usuario = ?1")
    Student getUsuario(Usuario usuario);
}
