package com.hatley.supermarket.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hatley.supermarket.admin.model.Supermarket;

public interface SuperMarketDAO extends JpaRepository<Supermarket, Integer>{

	
	@Modifying
	@Query("UPDATE Supermarket sm SET sm.isActivated = ?2 WHERE sm.id =?1")
	void setActivateStatusSuperMarket (Integer superMarketId , boolean isActivated);
}
