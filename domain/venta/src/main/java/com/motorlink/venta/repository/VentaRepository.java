/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.motorlink.venta.repository;

import com.motorlink.venta.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhams
 */
public interface VentaRepository extends JpaRepository<Venta, Long> {
    
}
