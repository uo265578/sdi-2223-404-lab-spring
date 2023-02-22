package com.uniovi.notaneitor.validators;
import com.uniovi.notaneitor.entities.Mark;
import com.uniovi.notaneitor.entities.User;
import com.uniovi.notaneitor.services.MarksService;
import com.uniovi.notaneitor.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

@Component
public class MarkFormValidator implements Validator {

    @Autowired
    private MarksService usersService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "Error.empty");

        if (mark.getScore() < 0 || mark.getScore() >10 ) { // rango de notas entre 0 y 10
            errors.rejectValue("score", "Error.mark.score");}

        if ( mark.getDescription().length() < 20 ) { // la descripción tiene que tener una longitud mínima de 20
            errors.rejectValue("description", "Error.mark.description");}
    }
}