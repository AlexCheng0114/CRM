package com.crm.sample.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.sample.api.repository.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	public List<Company> findByName(String name);
}
