/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.motorlink.venta.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jhams
 */

// Lombok Agreaga metodos setter and getter
@Data
// Anotacion de  entidad
@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Agregar anotacion de relacion entre entidades:
    @Column(length = 11, nullable = false)
    private Integer id_cliente;

    @Column(length = 11, nullable = false)
    private Integer id_usuario;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio_total;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_venta")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_venta;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fecha_creacion;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VentaDetalle> venta_detalle;
}
