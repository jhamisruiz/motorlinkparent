/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorlink.producto.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author jhams
 */

// Lombok Agreaga metodos setter and getter
@Data
// Anotacion de  entidad
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 10, nullable = false)
    private String codigo;
    
    @Column(length = 150, nullable = false)
    private String nombre;
    
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio_unitario;

}
