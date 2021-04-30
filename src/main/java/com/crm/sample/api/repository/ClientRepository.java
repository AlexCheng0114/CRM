package com.crm.sample.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crm.sample.api.repository.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	public List<Client> findByCompanyId(Long companyId);
	
	public List<Client> findByName(String name);
}
