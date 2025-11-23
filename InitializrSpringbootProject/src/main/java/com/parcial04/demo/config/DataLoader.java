/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.parcial04.demo.config;

/**
 *
 * @author dmeji
 */

import com.parcial04.demo.model.User;
import com.parcial04.demo.repository.UserRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    
    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            // Intentar leer el usuario 'admin' desde la base de datos
            var optAdmin = userRepository.findByUsername("admin");
            if (optAdmin.isPresent()) {
                User admin = optAdmin.get();
                System.out.println("Usuario 'admin' cargado desde la base de datos: email=" + admin.getEmail());
                // Si necesitas usar los datos de 'admin' para inicializar otra cosa,
                // hazlo aquí.
            } else {
                User u = new User();
                u.setUsername("admin");
                u.setPassword(encoder.encode("12345")); // contraseña
                u.setEmail("admin@correo.com");
                u.setFechaNacimiento(LocalDate.of(2000, 1, 1)); // El formato es año,mes,día
                u.setRole("ROLE_USER");
                userRepository.save(u);
            }
        };
    }
}
