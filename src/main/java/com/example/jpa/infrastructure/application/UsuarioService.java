package com.example.jpa.infrastructure.application;

import com.example.jpa.domain.Usuario;
import com.example.jpa.infrastructure.repository.UsuarioRepositorio;
import com.example.jpa.infrastructure.dto.output.UsuarioOutputDto;
import com.example.jpa.infrastructure.dto.input.UsuarioInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService implements UsuarioServicePort{

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public List<UsuarioOutputDto> getAll() {
        List<Usuario> usuarioList = usuarioRepositorio.findAll();
        List<UsuarioOutputDto> usuarioOutputDtoList = new ArrayList<>();
        for(Usuario uaxu : usuarioList) {
            UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(uaxu);
            usuarioOutputDtoList.add(usuarioOutputDto);
        }
        return usuarioOutputDtoList;
    }


    public UsuarioOutputDto getById(Integer id) throws Exception{
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("No encontrado"));
        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public List<UsuarioOutputDto> getByName(String name) throws Exception{
        List<Usuario> usuarioList = usuarioRepositorio.getByName(name);
        //Preguntar como hacer con streams
        List<UsuarioOutputDto> usuarioOutputDtoList = new ArrayList<>();
        for(Usuario uaxu : usuarioList) {
            UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(uaxu);
            usuarioOutputDtoList.add(usuarioOutputDto);
        }
        return usuarioOutputDtoList;
    }


    public UsuarioOutputDto anaidirUsuario(UsuarioInputDto u) {
        Usuario usuario = new Usuario(u);
        usuarioRepositorio.saveAndFlush(usuario);
        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public UsuarioOutputDto updateById(Integer id, UsuarioInputDto u) throws Exception{
        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new Exception("No encontrado"));
        if(usuario != null) {
            usuario.setActive(u.getActive());
            usuario.setCity(u.getCity());
            usuario.setCompany_email(u.getCompany_email());
            usuario.setName(u.getName());
            usuario.setSurname(u.getSurname());
            usuario.setUser(u.getUser());
            usuario.setPersonal_email(u.getPersonal_email());
            usuario.setPassword(u.getPassword());
            usuario.setCreated_date(u.getCreated_date());
        }
        usuarioRepositorio.saveAndFlush(usuario);

        UsuarioOutputDto usuarioOutputDto = new UsuarioOutputDto(usuario);
        return usuarioOutputDto;
    }


    public void deleteUsuarioById(Integer id) {
        usuarioRepositorio.delete(usuarioRepositorio.getById(id));
    }

}
