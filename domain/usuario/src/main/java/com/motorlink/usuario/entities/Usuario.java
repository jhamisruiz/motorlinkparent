/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorlink.usuario.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author jhams
 */
// Lombok Agreaga metodos setter and getter
@Data
// Anotacion de  entidad
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String usuario;

    @Column(length = 150, nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "email_verified_at", nullable = true)
    private Date email_verified_at;
    
    @Column(length = 250, nullable = false)
    private String password;

    @Column(columnDefinition = "LONGTEXT")
    private String remember_token;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    private Date created_at;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = true)
    private Date updated_at;
}
