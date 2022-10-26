/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinni.app_basespring.repository;

import com.vinni.app_basespring.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Vinni@
 */
@Repository
public interface  EstudianteRepository extends JpaRepository<Estudiante, Integer>  {
    public boolean existsByCedula(Long cedula);
    public Estudiante findByEmail(String email);
    
}
