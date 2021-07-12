package com.hatley.supermarket.admin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supermarket")
@Data
@NoArgsConstructor
public class Supermarket {

	public Supermarket (Integer id) {
		this.id = id ;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String arabicName ;
	private String englishName ;
	private String address ;
	private String image ;
	private Boolean isActivated = true ;
	
}
