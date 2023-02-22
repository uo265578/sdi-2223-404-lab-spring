package com.uniovi.notaneitor.validators;
import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.Professor;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.ProfessorsService;
import com.uniovi.notaneitor.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
@Component
public class ProfesorFormValidator implements Validator {
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProfessorsService profesorService;
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
    @Override
    public void validate(Object target, Errors errors) {
        Professor professor = (Professor) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

        char ultimoCaracter = professor.getDni().charAt(professor.getDni().length()-1);

        if (professor.getDni().length() != 9) { // dni igual a 9 caracteres
            errors.rejectValue("dni", "Error.professor.dni.length");
        }
        if(Character.isDigit(ultimoCaracter))  {
            errors.rejectValue("dni", "Error.professor.dni.digit");
        }
        if(usersService.getUserByDni(professor.getDni())!=null || profesorService.getProfessorByDni(professor.getDni())!=null) {
            errors.rejectValue("dni", "Error.professor.dni.duplicate");
        }

    }
}