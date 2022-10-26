package com.vinni.app_basespring.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import com.vinni.app_basespring.entity.Estudiante;
import com.vinni.app_basespring.service.EstudianteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Vinni
 */
@Controller
public class AutenticarController {
    @Autowired EstudianteService estudianteService;
    
    
    @GetMapping("/autenticar")
    public String getAutenticar() {
        return "autenticar";
    }
    
    @GetMapping("/autenticarOK")
    public String verPaginaDeInicio(Model modelo) {
        System.out.println("Modelo ");
        System.out.println(modelo.getAttribute("username"));
        
        List<Estudiante> estudiantes = estudianteService.readEstudiantes();
        modelo.addAttribute("estudiantes", estudiantes);
            //modelo.addAttribute("estudiantes", servicioEstudiante.ConsultaTodos());
        return "index";
    }
    
    @PostMapping("/autenticar")
    public String autenticar(@ModelAttribute("estudiante") Estudiante  estudianteDTO) {
        
        if (!estudianteService.autenticar(estudianteDTO)) {
            System.out.println(estudianteDTO);
            return "redirect:/autenticar?error";

        }
        return "redirect:/autenticarOK";
            
            
    }
    
}
