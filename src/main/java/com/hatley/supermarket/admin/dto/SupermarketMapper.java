package com.hatley.supermarket.admin.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hatley.supermarket.admin.model.Supermarket;

@Mapper
public interface SupermarketMapper {

	SupermarketMapper INSTANCE = Mappers.getMapper(SupermarketMapper.class);
	
	SupermarketDTO beanToDTO (Supermarket supermarket);
	Supermarket DTOToBean (SupermarketDTO supermarketDTO);
	
	List<SupermarketDTO> beansToDTOs (List<Supermarket> supermarkets); 
}
