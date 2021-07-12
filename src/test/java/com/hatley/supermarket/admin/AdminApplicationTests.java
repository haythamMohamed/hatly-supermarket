package com.hatley.supermarket.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.hatley.supermarket.admin.dto.SupermarketDTO;
import com.hatley.supermarket.admin.service.SupermarketService;

@SpringBootTest
@Transactional
@Commit
@TestMethodOrder(OrderAnnotation.class)
class AdminApplicationTests {

	@Autowired SupermarketService supermarketService;

	private SupermarketDTO getById (Integer id) {
		return supermarketService.getById(id);
	}
	
	@Order(1)
	@Test
	void addSuperMarket () {
		SupermarketDTO supermarketDTO = new SupermarketDTO();
		supermarketDTO.setArabicName("متجر 1");
		supermarketDTO.setEnglishName("store 1");
		supermarketDTO.setAddress("address");
		supermarketDTO.setImage("image");
		
		Integer id = supermarketService.saveSupermarket(supermarketDTO);
		assertNotNull(id);
		assertNotEquals(id, 0);
	}
	
	@Order(2)
	@Test
	void listSupermarkets () {
		List<SupermarketDTO> listOfMarkets = supermarketService.getAll();
		assertNotNull(listOfMarkets);
		assertEquals(listOfMarkets.size(), 1);
		assertNotNull(listOfMarkets.get(0).getId());
		assertEquals(listOfMarkets.get(0).getArabicName() , "متجر 1");
		assertEquals(listOfMarkets.get(0).getEnglishName() , "store 1");
		assertEquals(listOfMarkets.get(0).getAddress() , "address");
		assertEquals(listOfMarkets.get(0).getImage() , "image");
	}
	
	@Order(3)
	@Test
	void DeactivateSupermarket () {
		supermarketService.deactivateSupermarket(1);
		SupermarketDTO supermarketDTO = getById(1);
		assertEquals(supermarketDTO.getIsActivated(), false);
	}
	
	@Order(4)
	@Test
	void activateSupermarket () {
		supermarketService.activateSupermarket(1);
		SupermarketDTO supermarketDTO = getById(1);
		assertEquals(supermarketDTO.getIsActivated(), true);
	}
	
	@Order(5)
	@Test
	void updateSuperMarket () {
		SupermarketDTO supermarketDTO = new SupermarketDTO();
		supermarketDTO.setId(1);
		supermarketDTO.setArabicName("متجر 2");
		supermarketDTO.setEnglishName("store 2");
		supermarketDTO.setAddress("address 2");
		supermarketDTO.setImage("image 2");
		
		supermarketService.updateSupermarket(supermarketDTO);
		SupermarketDTO updatedSupermarketDTO = getById(1);
		
		assertNotNull(updatedSupermarketDTO);
		assertNotEquals(updatedSupermarketDTO.getId(), 0);
		assertNotNull(updatedSupermarketDTO.getId());
		assertEquals(updatedSupermarketDTO.getArabicName() , "متجر 2");
		assertEquals(updatedSupermarketDTO.getEnglishName() , "store 2");
		assertEquals(updatedSupermarketDTO.getAddress() , "address 2");
		assertEquals(updatedSupermarketDTO.getImage() , "image 2");
	}
	
	@Order(6)
	@Test
	void deleteSupermarket () {
		supermarketService.deleteSupermarket(1);
		SupermarketDTO supermarketDTO = getById(1);
		assertNull(supermarketDTO);
	}
	
}
