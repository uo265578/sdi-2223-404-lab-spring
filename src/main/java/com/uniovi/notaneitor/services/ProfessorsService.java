package com.uniovi.notaneitor.services;

import com.uniovi.notaneitor.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    public ArrayList<Professor> professorsList = new ArrayList<>();

    @PostConstruct
    public void init() {
        professorsList.add(new Professor(1L,"71741018", "Santi","Fidalgo","A"));
        professorsList.add(new Professor(2L,"89652682", "Maria", "Gonzalez","B"));
    }

    public List<Professor> getProfessors() {
        return professorsList;
    }

    public Professor getProfessor(Long id) {
        return professorsList.stream()
                .filter(professor -> professor.getId().equals(id)).findFirst().get();
    }

    public void addProfessor(Professor professor) {
        // Si Id es null le asignamos el último + 1 de la lista
        if (professor.getId() == null) {
            professor.setId(professorsList.get(professorsList.size() - 1).getId() + 1);
        }
        professorsList.add(professor);
    }

    public void deleteProfessor(Long id) {
        professorsList.removeIf(professor -> professor.getId().equals(id));
    }

    @Override
    public String toString() {
        String info="";
        for (int i=0;i<professorsList.size();i++){
            info+=professorsList.get(i).toString();
        }
        return info;
    }


}