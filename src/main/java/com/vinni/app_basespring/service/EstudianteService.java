/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vinni.app_basespring.service;

import com.vinni.app_basespring.entity.Estudiante;
import com.vinni.app_basespring.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.log4j.Log4j2;
/**
 *
 * @author Vinni@
 */
@Service
@Log4j2
public class EstudianteService {
    
    @PersistenceContext
    private EntityManager entityManager;
        
    @Autowired
    private EstudianteRepository estudianteRepository;
    
      @Transactional
    public boolean createEstudiante(Estudiante estudiante){
        try {
            if (!estudianteRepository.existsByCedula(estudiante.getCedula())){
                
                estudianteRepository.save(estudiante);
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            throw e;
        }
    }

    public List<Estudiante> readEstudiantes(){
        return estudianteRepository.findAll();
    }

    @Transactional
    public String updateEstudiante(Estudiante estudiante){
        if (estudianteRepository.existsByCedula(estudiante.getCedula())){
             estudianteRepository.save(estudiante);
                return "Estudiante record updated.";
            
        }else {
            return "Estudiante does not exists in the database.";
        }
    }

    @Transactional
    public String deleteEstudiante(Estudiante estudiante){
        if (estudianteRepository.existsByCedula(estudiante.getCedula())){
            estudianteRepository.delete(estudiante);
            return "Estudiante record deleted successfully.";
        }else {
            return "Estudiante does not exist";
        }
    }
    public boolean autenticar(Estudiante estudiante){
     Estudiante estudianteN = estudianteRepository.findByEmail(estudiante.getEmail());
     if (estudiante.getEmail().equals(estudianteN.getEmail()) && 
         estudiante.getClave().equals(estudianteN.getClave())){
         return true; 
     }
     return false;
    }
    public boolean autenticarX(Estudiante estudiante){
        String sql = "select  e from Estudiante where e.email = :email and e.clave = :clave";
        Query q = entityManager.createQuery(sql);
        q.setParameter("email", estudiante.getEmail());
        q.setParameter("clave", estudiante.getClave());
        try {
            estudiante = (Estudiante)q.getSingleResult();
            if (estudiante != null){
                return true;
            }
        }catch (Exception e ){
            log.error("No se encuentra el estudiante", e);
        }
                    
        return false;
    }
}
