package com.example.infsystem.util;

import com.example.infsystem.models.Person;
import com.example.infsystem.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        try{
            personService.loadUserByUsername(person.getUsername());
        }catch (UsernameNotFoundException e){
            return;
        }

        errors.rejectValue("username", "", "Человек с таким логином уже существует");
    }
}
