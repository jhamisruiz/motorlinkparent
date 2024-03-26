/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springboot/ApplRunner.java to edit this template
 */
package com.motorlink.cliente.service;

import com.motorlink.cliente.entities.Cliente;
import com.motorlink.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhams
 */
@Component
public class ClienteAppRunner implements ApplicationRunner {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Override
    public void run(ApplicationArguments aa) throws Exception {
        if (clienteRepository.count() == 0) {
            // Insertar registros si la tabla está vacía
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Juan");
            cliente1.setApellido("Perez");
            cliente1.setNumero_documento("45259636");
            cliente1.setTelefono("963258741");
            clienteRepository.save(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Jhoel");
            cliente2.setApellido("Castillo");
            cliente2.setNumero_documento("10748596321");
            cliente2.setTelefono("986532147");
            clienteRepository.save(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Luocho");
            cliente3.setApellido("Quispe");
            cliente3.setNumero_documento("85967423");
            cliente3.setTelefono("936928741");
            clienteRepository.save(cliente3);

            //("Se han insertado 3 registros en la tabla cliente.");
        }
    }
    
}
