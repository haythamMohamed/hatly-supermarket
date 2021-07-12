package com.hatley.supermarket.admin.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hatley.supermarket.admin.dao.SuperMarketDAO;
import com.hatley.supermarket.admin.dto.SupermarketDTO;
import com.hatley.supermarket.admin.dto.SupermarketMapper;
import com.hatley.supermarket.admin.model.Supermarket;
import com.hatley.supermarket.admin.service.SupermarketService;

@Service
@Transactional
public class SupermarketServiceImpl implements SupermarketService {

	@Autowired SuperMarketDAO superMarketDAO;
	
	@Override
	public Integer saveSupermarket(SupermarketDTO supermarketDTO) {
		Supermarket supermarket = SupermarketMapper.INSTANCE.DTOToBean(supermarketDTO);
		superMarketDAO.save(supermarket);
		return supermarket.getId();
	}

	@Override
	public void updateSupermarket(SupermarketDTO supermarketDTO) {
		Supermarket supermarket = SupermarketMapper.INSTANCE.DTOToBean(supermarketDTO);
		superMarketDAO.save(supermarket);
	}

	@Override
	public void activateSupermarket(Integer supermarketId) {
		superMarketDAO.setActivateStatusSuperMarket(supermarketId, true);
	}

	@Override
	public void deactivateSupermarket(Integer supermarketId) {
		superMarketDAO.setActivateStatusSuperMarket(supermarketId, false);
	}

	@Override
	public List<SupermarketDTO> getAll() {
		List<Supermarket> supermarkets = superMarketDAO.findAll();
		return SupermarketMapper.INSTANCE.beansToDTOs(supermarkets);
	}

	@Override
	public SupermarketDTO getById(Integer supermarketId) {
		Optional<Supermarket> optionalSupermarket = superMarketDAO.findById(supermarketId);
		return SupermarketMapper.INSTANCE.beanToDTO(optionalSupermarket.orElse(null)) ;
	}

	@Override
	public void deleteSupermarket(Integer supermarketId) {
		superMarketDAO.delete(new Supermarket(supermarketId));
	}

}
