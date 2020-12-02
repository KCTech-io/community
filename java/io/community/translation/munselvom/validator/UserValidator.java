package io.community.translation.munselvom.validator;


import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Errors;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.community.translation.munselvom.auth.UserForm;
import io.community.translation.munselvom.auth.UserList;
import io.community.translation.munselvom.auth.UserService;

@Component
public class UserValidator implements Validator {
	
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == UserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm userForm = (UserForm) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.userForm.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "NotEmpty.userForm.userId");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.userForm.gender");
		
        if (!this.emailValidator.isValid(userForm.getEmail())) {
        	errors.rejectValue("email","Pattern.userForm.email");
        } 
        else {
        	UserList dbUser = userService.getUserByEmail(userForm.getEmail());
        	if (dbUser != null) {
        		errors.rejectValue("email","Duplicate.userForm.email");
        	}
        }
        if (!errors.hasFieldErrors("userId")) {
        	UserList dbUser = userService.getUserByName(userForm.getUserId());
        	if (dbUser != null) {
        		errors.rejectValue("userId", "Duplicate.userForm.userId");
        	}
        }
        if (!errors.hasErrors()) {
        	if (!userForm.getConfirmPassword().equals(userForm.getPassword())) {
        		errors.rejectValue("confirmPassword", "Match.userForm.confirmPassword");
        	}
        }
	}
	
}
