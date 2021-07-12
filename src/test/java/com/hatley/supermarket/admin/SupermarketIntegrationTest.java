package com.hatley.supermarket.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.hatley.supermarket.admin.dto.SupermarketDTO;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
@Commit
@TestMethodOrder(OrderAnnotation.class)
public class SupermarketIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Order(1)
	@Test
	public void testSaveApi () {
		
		SupermarketDTO supermarketDTO = new SupermarketDTO();
		supermarketDTO.setArabicName("متجر 1");
		supermarketDTO.setEnglishName("store 1");
		supermarketDTO.setAddress("address");
		supermarketDTO.setImage("image");
		
		HttpEntity<SupermarketDTO> httpEntity = new HttpEntity<SupermarketDTO>(supermarketDTO);
		
		ResponseEntity<Integer> responseEntity = testRestTemplate.exchange("/super-market/", HttpMethod.POST, httpEntity, Integer.class);
	
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody() , 1 );
	}
	
	@Order(2)
	@Test
	public void testGetAll () {
		ResponseEntity<List<SupermarketDTO>> responseEntity = testRestTemplate.exchange("/super-market/", HttpMethod.GET, null , new ParameterizedTypeReference<List<SupermarketDTO>>(){});

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().size() , 1 );
		assertEquals(responseEntity.getBody().get(0).getId() , 1 );
	}
	
	
	@Order(3)
	@Test
	public void testGetById () {
		ResponseEntity<SupermarketDTO> responseEntity = testRestTemplate.exchange("/super-market/1", HttpMethod.GET, null , SupermarketDTO.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertNotNull(responseEntity.getBody());
		assertEquals(responseEntity.getBody().getId() , 1 );
		assertEquals(responseEntity.getBody().getArabicName() , "متجر 1");
		assertEquals(responseEntity.getBody().getEnglishName() , "store 1" );
	}
	
	@Order(4)
	@Test
	public void activateSupermarket () {
		SupermarketDTO supermarketDTO = new SupermarketDTO();
		supermarketDTO.setId(1);
		
		HttpEntity<SupermarketDTO> httpEntity = new HttpEntity<SupermarketDTO>(supermarketDTO);
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/super-market/activate", HttpMethod.PUT, httpEntity , Void.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

		ResponseEntity<SupermarketDTO> getSupermarketEntity = testRestTemplate.exchange("/super-market/1", HttpMethod.GET, null , SupermarketDTO.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(getSupermarketEntity.getBody().getIsActivated() , true);
	}
	
	
	@Order(5)
	@Test
	public void deactivateSupermarket () {
		SupermarketDTO supermarketDTO = new SupermarketDTO();
//		supermarketDTO.setId(1);
		
		HttpEntity<SupermarketDTO> httpEntity = new HttpEntity<SupermarketDTO>(supermarketDTO);
		
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/super-market/deactivate", HttpMethod.PUT, httpEntity , Void.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

		ResponseEntity<SupermarketDTO> getSupermarketEntity = testRestTemplate.exchange("/super-market/1", HttpMethod.GET, null , SupermarketDTO.class);
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(getSupermarketEntity.getBody().getIsActivated() , false);
	}
	
	@Order(6)
	@Test
	public void deleteSupermarket () {
		
		ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/super-market/1", HttpMethod.DELETE, null , Void.class);

		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

		ResponseEntity<List<SupermarketDTO>> getSupermarketEntity = testRestTemplate.exchange("/super-market/", HttpMethod.GET, null , new ParameterizedTypeReference<List<SupermarketDTO>>(){});
		assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
		assertEquals(getSupermarketEntity.getBody().size() , 0);
	}
}
