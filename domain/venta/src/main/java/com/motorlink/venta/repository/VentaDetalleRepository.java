/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.motorlink.venta.repository;

import com.motorlink.venta.entities.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhams
 */
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
    
}
