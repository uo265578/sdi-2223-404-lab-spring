package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    @Autowired
    private ProfessorsRepository professorsRepository;

    public List<Professor> getProfessors() {
        List<Professor> profesores = new ArrayList<>();
        professorsRepository.findAll().forEach(profesores::add);
        return profesores;
    }

    public Professor getProfessor(Long id) {
        return professorsRepository.findById(id).get();
    }

    public void addProfessor(Professor professor) {
        // Si en Id es null le asignamos el último + 1 de la lista
        professorsRepository.save(professor);;
    }

    public void deleteProfessor(Long id) {

        professorsRepository.deleteById(id);
    }

    public Professor getProfessorByDni(String dni) {
        return professorsRepository.findByDni(dni);
    }

    @Override
    public String toString() {
        List<Professor> professorsList = new ArrayList<>();
        professorsRepository.findAll().forEach(professorsList::add);
        String info="";
        for (int i=0;i<professorsList.size();i++){
            info+=professorsList.get(i).toString();
        }
        return info;
    }


}