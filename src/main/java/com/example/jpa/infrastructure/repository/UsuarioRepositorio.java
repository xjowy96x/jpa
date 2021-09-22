package com.example.jpa.infrastructure.repository;

import com.example.jpa.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.name = ?1")
    List<Usuario> getByName(String name);

    //Se podia usar directamente esto en lugar del getByName
    List<Usuario> findByName(String name);

}
