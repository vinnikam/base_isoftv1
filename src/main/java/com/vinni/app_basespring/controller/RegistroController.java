package com.vinni.app_basespring.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
;


import com.vinni.app_basespring.service.EstudianteService;
import com.vinni.app_basespring.entity.Estudiante;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Sala_06
 */
@Controller
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    EstudianteService estudianteService;
    
    @PostMapping
    public String registrarEstudiante(@ModelAttribute("estudiante") Estudiante estudianteDTO) {
        
        if (estudianteService.createEstudiante(estudianteDTO)){
            System.out.println(estudianteDTO);
            return "redirect:/registro?exito";
        }
        return "redirect:/registro?error";

    }

    @ModelAttribute("estudiante")
    public Estudiante retornarNuevoUsuarioRegistroDTO() {
            return new Estudiante();
    }
        
    @GetMapping
    public String getRegistro() {
        return "registro";
    }
}
