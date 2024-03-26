/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.motorlink.cliente.controller;

import com.motorlink.cliente.entities.Cliente;
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
import com.motorlink.cliente.repository.ClienteRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jhams
 */
@RestController
@RequestMapping("/cliente")
public class ClienteRestController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping()
    public ResponseEntity<List<Cliente>> list() {
        //return clienteRepository.findAll();
        List<Cliente> findAll = clienteRepository.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Integer id) {
        return clienteRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody Cliente input) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNombre(input.getNombre());
            clienteExistente.setApellido(input.getApellido());
            clienteExistente.setNumero_documento(input.getNumero_documento());
            clienteExistente.setTelefono(input.getTelefono());
            Cliente save = clienteRepository.save(clienteExistente);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cliente input, BindingResult result) {
        //Cliente save = clienteRepository.save(input);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(input));
    }

    @DeleteMapping("/{id}")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        clienteRepository.deleteById(id);
        return new ResponseEntity<>("User deleted Successfully.", HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
