package com.victor.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor.domain.Cliente;

@Repository 
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {	
	
	Cliente findByEmail(String email);
}
