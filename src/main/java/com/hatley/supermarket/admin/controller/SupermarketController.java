package com.hatley.supermarket.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatley.supermarket.admin.controller.validator.ValidateActivateSupermarket;
import com.hatley.supermarket.admin.controller.validator.ValidateDeactivateSupermarket;
import com.hatley.supermarket.admin.dto.SupermarketDTO;
import com.hatley.supermarket.admin.service.SupermarketService;

@RestController
@RequestMapping("/super-market")
public class SupermarketController {

	@Autowired SupermarketService supermarketService;
	
	@Autowired ValidateActivateSupermarket validateActivateSupermarket;
	@Autowired ValidateDeactivateSupermarket validateDeactivateSupermarket ;
	
	@GetMapping("/")
	public List<SupermarketDTO> getAll (){
		return supermarketService.getAll();
	}
	
	@GetMapping("/{id}")
	public SupermarketDTO getById (@PathVariable(name = "id" ) Integer id) {
		return supermarketService.getById(id);
	}
	
	@PostMapping("/")
	public Integer saveSupermarket (@RequestBody SupermarketDTO supermarketDTO) {
		return supermarketService.saveSupermarket(supermarketDTO);
	}
	
	@PutMapping("/")
	public void updateSupermarket (@RequestBody SupermarketDTO supermarketDTO) {
		supermarketService.updateSupermarket(supermarketDTO);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById (@PathVariable(name = "id" ) Integer id) {
		supermarketService.deleteSupermarket(id);
	}
	
	@PutMapping("/activate")
	public void activateSupermarket (@RequestBody SupermarketDTO supermarketDTO , BindingResult bindingResult) throws BindException {
		validateActivateSupermarket.validate(supermarketDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		supermarketService.activateSupermarket(supermarketDTO.getId());
	}
	
	@PutMapping("/deactivate")
	public void deactivateSupermarket (@RequestBody SupermarketDTO supermarketDTO, BindingResult bindingResult) throws BindException {
		validateDeactivateSupermarket.validate(supermarketDTO, bindingResult);
		if(bindingResult.hasErrors()) {
			throw new BindException(bindingResult);
		}
		supermarketService.deactivateSupermarket(supermarketDTO.getId());
	}
}
