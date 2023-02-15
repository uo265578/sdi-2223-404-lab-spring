package com.uniovi.notaneitor.controllers;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.services.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorsController {

    @Autowired //Inyectar el servicio
    private ProfessorsService professorsService;

    @RequestMapping("/professor/list")
    public String getList() {
        return professorsService.getProfessors().toString();
    }

    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return professor.toString();
    }

    @RequestMapping(value = "/professor/add")
    public String getProfessor() {
        return "Profesor añadido";
    }


    // --- editar
    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "Profesor editado: " + id;
    }

    @RequestMapping(value = "/professor/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute Professor professor, @PathVariable Long id) {
        String previous = professorsService.getProfessor(id).toString();
        professor.setId(id);
        professorsService.addProfessor(professor);

        return "Profesor editado: " + previous.toString() + " a: " + professor.toString();
    }



    // --- ver detalle

    @RequestMapping("/professor/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return professorsService.getProfessor(id).toString();
    }


    // --- borrar
    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id) {
        professorsService.deleteProfessor(id);
        return "deleted ";
    }


}