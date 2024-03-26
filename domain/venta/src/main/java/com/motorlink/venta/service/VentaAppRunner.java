/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springboot/ApplRunner.java to edit this template
 */
package com.motorlink.venta.service;

import com.motorlink.venta.entities.Venta;
import com.motorlink.venta.entities.VentaDetalle;
import com.motorlink.venta.repository.VentaDetalleRepository;
import com.motorlink.venta.repository.VentaRepository;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhams
 */
@Component
public class VentaAppRunner implements ApplicationRunner {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    @Override
    public void run(ApplicationArguments aa) throws Exception {
        // Verificar si la tabla está vacía
        if (ventaRepository.count() == 0) {
            Venta venta1 = new Venta();
            venta1.setId_cliente(1);
            venta1.setId_usuario(1);
            venta1.setPrecio_total(new BigDecimal("100.00"));
            venta1.setFecha_venta(new Date());
            venta1.setFecha_creacion(new Date());
            ventaRepository.save(venta1);

            Venta venta2 = new Venta();
            venta2.setId_cliente(2);
            venta2.setId_usuario(2);
            venta2.setPrecio_total(new BigDecimal("200.00"));
            venta2.setFecha_venta(new Date());
            venta2.setFecha_creacion(new Date());
            ventaRepository.save(venta2);

            Venta venta3 = new Venta();
            venta3.setId_cliente(3);
            venta3.setId_usuario(3);
            venta3.setPrecio_total(new BigDecimal("300.00"));
            venta3.setFecha_venta(new Date());
            venta3.setFecha_creacion(new Date());
            ventaRepository.save(venta3);            
        }
        
        if (ventaDetalleRepository.count() == 0) {
            VentaDetalle ventaDetalle1 = new VentaDetalle();
            ventaDetalle1.setId_venta(1); 
            ventaDetalle1.setId_producto(1); 
            ventaDetalleRepository.save(ventaDetalle1);

            VentaDetalle ventaDetalle2 = new VentaDetalle();
            ventaDetalle2.setId_venta(1); 
            ventaDetalle2.setId_producto(2);
            ventaDetalleRepository.save(ventaDetalle2);

            VentaDetalle ventaDetalle3 = new VentaDetalle();
            ventaDetalle3.setId_venta(1);
            ventaDetalle3.setId_producto(3); 
            ventaDetalleRepository.save(ventaDetalle3);

            VentaDetalle ventaDetalle4 = new VentaDetalle();
            ventaDetalle4.setId_venta(2); 
            ventaDetalle4.setId_producto(1); 
            ventaDetalleRepository.save(ventaDetalle4);

            VentaDetalle ventaDetalle5 = new VentaDetalle();
            ventaDetalle5.setId_venta(2); 
            ventaDetalle5.setId_producto(2);
            ventaDetalleRepository.save(ventaDetalle5);

            VentaDetalle ventaDetalle6 = new VentaDetalle();
            ventaDetalle6.setId_venta(2);
            ventaDetalle6.setId_producto(3); 
            ventaDetalleRepository.save(ventaDetalle6);

            VentaDetalle ventaDetalle7 = new VentaDetalle();
            ventaDetalle7.setId_venta(3); 
            ventaDetalle7.setId_producto(1); 
            ventaDetalleRepository.save(ventaDetalle7);

            VentaDetalle ventaDetalle8 = new VentaDetalle();
            ventaDetalle8.setId_venta(3); 
            ventaDetalle8.setId_producto(2);
            ventaDetalleRepository.save(ventaDetalle8);

            VentaDetalle ventaDetalle9 = new VentaDetalle();
            ventaDetalle9.setId_venta(3);
            ventaDetalle9.setId_producto(3); 
            ventaDetalleRepository.save(ventaDetalle9);
        }
    }

}
