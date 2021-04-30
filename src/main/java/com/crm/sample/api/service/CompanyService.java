package com.crm.sample.api.service;

import java.util.List;

import com.crm.sample.api.controller.vo.CompanyDTO;

public interface CompanyService {
	public void updateCompany(CompanyDTO companyVo);
	public void deleteCompany(Long id);
	public void addCompany(CompanyDTO companyVo);
	public List<CompanyDTO> findCompanyByName(String name);
	public List<CompanyDTO> findCompanyAll();
	
}
