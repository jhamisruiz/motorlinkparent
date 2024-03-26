/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.motorlink.producto.controller;

import com.motorlink.producto.entities.Producto;
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
import com.motorlink.producto.repository.ProductoRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;

/**
 *
 * @author jhams
 */
@RestController
@RequestMapping("/producto")
public class ProductoRestController {

    @Autowired
    ProductoRepository productosRepository;

    @GetMapping()
    public ResponseEntity<List<Producto>> list() {
        List<Producto> findAll = productosRepository.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Long id) {
        return productosRepository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Producto input) {
        Optional<Producto> optionalproductos = productosRepository.findById(id);
        if (optionalproductos.isPresent()) {
            Producto newproductos = optionalproductos.get();
            newproductos.setCodigo(input.getCodigo());
            newproductos.setNombre(input.getNombre());
            newproductos.setPrecio_unitario(input.getPrecio_unitario());
            Producto save = productosRepository.save(newproductos);
            return new ResponseEntity<>(save, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto input, BindingResult result) {
        // Producto save = productosRepository.save(input);
        // return ResponseEntity.ok(save);
        if (result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productosRepository.save(input));
    }

    @DeleteMapping("/{id}")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        productosRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
