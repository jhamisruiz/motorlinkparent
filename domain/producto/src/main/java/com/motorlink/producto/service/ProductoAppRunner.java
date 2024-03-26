/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springboot/ApplRunner.java to edit this template
 */
package com.motorlink.producto.service;

import com.motorlink.producto.entities.Producto;
import com.motorlink.producto.repository.ProductoRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhams
 */
@Component
public class ProductoAppRunner implements ApplicationRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(ApplicationArguments aa) throws Exception {
        // Verificar si la tabla está vacía
        if (productoRepository.count() == 0) {
            // Insertar registros si la tabla está vacía
            Producto producto1 = new Producto();
            producto1.setCodigo("P-001");
            producto1.setNombre("Camiseta");
            producto1.setPrecio_unitario(new BigDecimal(25.99));
            productoRepository.save(producto1);

            Producto producto2 = new Producto();
            producto2.setCodigo("P-002");
            producto2.setNombre("Pantalón");
            producto2.setPrecio_unitario(new BigDecimal(39.99));
            productoRepository.save(producto2);

            Producto producto3 = new Producto();
            producto3.setCodigo("P-003");
            producto3.setNombre("Zapatos");
            producto3.setPrecio_unitario(new BigDecimal(59.99));
            productoRepository.save(producto3);

            System.out.println("Se han insertado 3 registros en la tabla producto.");
        }
    }

}
