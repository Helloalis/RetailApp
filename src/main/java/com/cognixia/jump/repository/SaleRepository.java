package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Sale;
import com.cognixia.jump.model.User;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
	
	public List<Sale> findSaleByUser(User user);
	
}