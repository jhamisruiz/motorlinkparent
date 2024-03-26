/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.motorlink.cliente.repository;

import com.motorlink.cliente.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhams
 */
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
}
