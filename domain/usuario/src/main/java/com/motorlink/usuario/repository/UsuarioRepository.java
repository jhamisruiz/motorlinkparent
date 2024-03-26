/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.motorlink.usuario.repository;

import com.motorlink.usuario.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhams
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
