package com.mycrud.Crud.controller;

import com.mycrud.Crud.Model.Usuario;
import com.mycrud.Crud.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public record GreatingsController(@Autowired UsuarioRepository usuarioRepository) {

    public GreatingsController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/mostrarnome/{name}")
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Curso Spring Boot API: " + name + "!";
    }

    @GetMapping(value = "listartodos")
    @ResponseBody /* Retorna os dados para o corpo da resposta */
    public ResponseEntity<List<Usuario>> listaUsuario() {

        List<Usuario> usuarios = usuarioRepository.findAll(); /* executa a consulta no banco de dados */

        return new ResponseEntity<>(usuarios, HttpStatus.OK); /* Retorna a lista em JSON */
    }

    @PostMapping(value = "salvarinformacao")/*Mapeia a url*/
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /*Recebe os dados para salvar*/

        Usuario user = usuarioRepository.save(usuario);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }


}