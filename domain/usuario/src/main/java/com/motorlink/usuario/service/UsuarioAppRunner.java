/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springboot/ApplRunner.java to edit this template
 */
package com.motorlink.usuario.service;

import com.motorlink.usuario.entities.Usuario;
import com.motorlink.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author jhams
 */
@Component
public class UsuarioAppRunner implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments aa) throws Exception {
        if (usuarioRepository.count() == 0) {
            // Insertar registros si la tabla está vacía
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Juan Perez");
            usuario1.setUsuario("juanperez");
            usuario1.setEmail("juan@example.com");
            usuario1.setPassword("contrasenia1");
            usuario1.setRemember_token("token1");
            usuarioRepository.save(usuario1);

            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Maria Garcia");
            usuario2.setUsuario("mariagarcia");
            usuario2.setEmail("maria@example.com");
            usuario2.setPassword("contrasenia2");
            usuario2.setRemember_token("token2");
            usuarioRepository.save(usuario2);

            Usuario usuario3 = new Usuario();
            usuario3.setNombre("Carlos Ramirez");
            usuario3.setUsuario("carlosramirez");
            usuario3.setEmail("carlos@example.com");
            usuario3.setPassword("contrasenia3");
            usuario3.setRemember_token("token3");
            usuarioRepository.save(usuario3);

            System.out.println("Se han insertado 3 registros en la tabla usuario.");
        }
    }

}
