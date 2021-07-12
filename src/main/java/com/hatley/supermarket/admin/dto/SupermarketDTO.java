package com.hatley.supermarket.admin.dto;

import lombok.Data;

@Data
public class SupermarketDTO {

	private Integer id ;
	private String arabicName ;
	private String englishName ;
	private String address ;
	private String image ;
	private Boolean isActivated ;
	
}
