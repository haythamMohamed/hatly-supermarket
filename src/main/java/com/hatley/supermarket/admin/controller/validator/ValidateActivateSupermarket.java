package com.hatley.supermarket.admin.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hatley.supermarket.admin.dto.SupermarketDTO;
import com.hatley.supermarket.admin.service.SupermarketService;

@Component
public class ValidateActivateSupermarket implements Validator{

	@Autowired SupermarketService supermarketService ; 
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SupermarketDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		SupermarketDTO supermarketDTO = (SupermarketDTO) target ;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "Id can't be null or empty");
		
		if(supermarketDTO.getId() != null) {
			SupermarketDTO dto =  supermarketService.getById(supermarketDTO.getId());
			if(dto != null && dto.getIsActivated() != null && dto.getIsActivated()) {
				errors.reject("id", "This supermarket is already activated");
			}
		}
	}

}
