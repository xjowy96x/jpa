package com.example.jpa.infrastructure.repository;

import com.example.jpa.domain.Asignatura;
import com.example.jpa.domain.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, String> {

//    @Query("select s from Profesor p join Student s on p.id_profesor=s.id_profesor where p.id_profesor = ?1")
//    Profesor getEstudiantes(String id_profesor);

//    @Query("select count(p.id_profesor) from Profesor p join Usuario u on p.id_user=u.id_user where p.id_user = ?1")
//    int getIdExisteProfesor(Integer id_user);

//    List<Profesor> findByUsuario(Usuario id);
//    Se podia usar directamente esto en lugar del getByName
//    List<Usuario> findByName(String name);


}
