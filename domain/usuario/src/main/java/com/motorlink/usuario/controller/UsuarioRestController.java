/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.motorlink.usuario.controller;

import com.motorlink.usuario.entities.Usuario;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.motorlink.usuario.repository.UsuarioRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jhams
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> list() {
        List<Usuario> findAll = usuarioRepository.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            return new ResponseEntity<>(usuarioOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Usuario input) {

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setNombre(input.getNombre());
            usuarioExistente.setUsuario(input.getUsuario());
            usuarioExistente.setEmail(input.getEmail());
            usuarioExistente.setEmail_verified_at(input.getEmail_verified_at());
            usuarioExistente.setPassword(input.getPassword());
            usuarioExistente.setRemember_token(input.getRemember_token());
            usuarioExistente.setCreated_at(input.getCreated_at());
            usuarioExistente.setUpdated_at(input.getUpdated_at());
            Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
            return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario input, BindingResult result) {
        //Usuario save = usuarioRepository.save(input);
        //return ResponseEntity.ok(save);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(input));
    }

    @DeleteMapping("/{id}")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>("User deleted Successfully.",HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
