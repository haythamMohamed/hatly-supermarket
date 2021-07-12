package com.hatley.supermarket.admin.service;

import java.util.List;

import com.hatley.supermarket.admin.dto.SupermarketDTO;

public interface SupermarketService {

	Integer saveSupermarket (SupermarketDTO supermarketDTO);
	void updateSupermarket (SupermarketDTO supermarketDTO);
	void deleteSupermarket (Integer supermarketId);
	void activateSupermarket (Integer supermarketId) ;
	void deactivateSupermarket (Integer supermarketId);
	
	List<SupermarketDTO> getAll ();
	SupermarketDTO getById (Integer supermarketId);
}
