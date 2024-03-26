/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.motorlink.producto.repository;

import com.motorlink.producto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhams
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
