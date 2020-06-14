package it.uniroma3.siw.Silph.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.Silph.model.User;


@Component
public class UserValidator implements Validator {
	final Integer MAX_NAME_LENGTH = 100;
    final Integer MIN_NAME_LENGTH = 2;

    @Autowired
    UserService userService;

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        String firstName = user.getFirstName().trim();
        String lastName = user.getLastName().trim();
        
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "required");

        if (firstName.length() < MIN_NAME_LENGTH || firstName.length() > MAX_NAME_LENGTH)
            errors.rejectValue("firstName", "size");

       if (lastName.length() < MIN_NAME_LENGTH || lastName.length() > MAX_NAME_LENGTH)
            errors.rejectValue("lastName", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
}