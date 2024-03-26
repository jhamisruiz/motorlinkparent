/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.motorlink.venta.controller;

import com.motorlink.venta.entities.Venta;
import com.motorlink.venta.entities.VentaDetalle;
import com.motorlink.venta.repository.VentaDetalleRepository;
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
import com.motorlink.venta.repository.VentaRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jhams
 */
@RestController
@RequestMapping("/venta")
public class VentaRestController {

    @Autowired
    VentaRepository ventaRepository;

    @Autowired
    VentaDetalleRepository ventaDetalleRepository;

    @GetMapping()
    public ResponseEntity<List<Venta>> list() {
        //return clienteRepository.findAll();
        List<Venta> findAll = ventaRepository.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }
//    public ResponseEntity<?> List(@RequestParam(required = false) Long id) {
//        if (id != null && id > 0) {
//            Optional<Venta> ventaOptional = ventaRepository.findById(id);
//            if (ventaOptional.isPresent()) {
//                return ResponseEntity.ok(ventaOptional.get());
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        } else {
//            List<Venta> findAll = ventaRepository.findAll();
//            if (findAll == null || findAll.isEmpty()) {
//                return ResponseEntity.noContent().build();
//            } else {
//                return ResponseEntity.ok(findAll);
//            }
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) {

        Optional<Venta> venta = ventaRepository.findById(id);
        return venta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable long id, @RequestBody Venta input) {

        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        if (optionalVenta.isPresent()) {
            Venta existingVenta = optionalVenta.get();

            // Actualizar los campos de la venta
            existingVenta.setId_cliente(input.getId_cliente());
            existingVenta.setId_usuario(input.getId_usuario());
            existingVenta.setPrecio_total(input.getPrecio_total());
            existingVenta.setFecha_venta(input.getFecha_venta());
            existingVenta.setFecha_creacion(input.getFecha_creacion());

            // Actualizar los detalles de la venta
            List<VentaDetalle> existingDetalles = existingVenta.getVenta_detalle();
            List<VentaDetalle> updatedDetalles = input.getVenta_detalle();

            // Eliminar los detalles que no están presentes en la solicitud
            existingDetalles.removeIf(detalle -> !updatedDetalles.contains(detalle));

            // Agregar nuevos detalles o actualizar los existentes
            for (VentaDetalle updatedDetalle : updatedDetalles) {
                if (!existingDetalles.contains(updatedDetalle)) {
                    updatedDetalle.setVenta(existingVenta);
                    existingDetalles.add(updatedDetalle);
                }
            }

            Venta savedVenta = ventaRepository.save(existingVenta);
            return ResponseEntity.ok(savedVenta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Venta input, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        //return ResponseEntity.status(HttpStatus.CREATED).body(ventaRepository.save(input));
        Venta nuevaVenta = new Venta();
        nuevaVenta.setId_cliente(input.getId_cliente());
        nuevaVenta.setId_usuario(input.getId_usuario());
        nuevaVenta.setPrecio_total(input.getPrecio_total());
        nuevaVenta.setFecha_venta(input.getFecha_venta());
        nuevaVenta.setFecha_creacion(input.getFecha_creacion());

        // Guardar la nueva venta en la base de datos
        Venta savedVenta = ventaRepository.save(nuevaVenta);

        Integer ventaId = savedVenta.getId();
        List<VentaDetalle> ventaDetalles = input.getVenta_detalle();

        if (ventaDetalles != null) {

            for (VentaDetalle detalle : ventaDetalles) {
                detalle.setId_venta(ventaId);
            }
            input.setVenta_detalle(ventaDetalles);

            List<VentaDetalle> ventaDetallesResponse = ventaDetalleRepository.saveAll(ventaDetalles); // Guardar detalles de venta
            savedVenta.setVenta_detalle(ventaDetallesResponse);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVenta);
    }

    @DeleteMapping("/{id}")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable long id) {
        Optional<Venta> optionalVenta = ventaRepository.findById(id);
        if (optionalVenta.isPresent()) {
            Venta venta = optionalVenta.get();
            ventaRepository.delete(venta); // Eliminar la venta y sus detalles de venta asociados
            return new ResponseEntity<>("User deleted Successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
