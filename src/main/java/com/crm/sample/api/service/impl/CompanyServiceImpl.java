package com.crm.sample.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.crm.sample.api.controller.vo.CompanyDTO;
import com.crm.sample.api.service.CompanyService;
import com.crm.sample.repository.CompanyRepository;
import com.crm.sample.repository.model.Company;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDIT')")
	public void addCompany(CompanyDTO companyVo) {
		log.info(String.format("addCompany:%s", companyVo.toString()));
		Company companyBo = new Company();
		BeanUtils.copyProperties(companyVo, companyBo);
		companyBo.setId(null);
		companyRepository.saveAndFlush(companyBo);
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDIT')")
	public void updateCompany(CompanyDTO companyVo) {
		log.info(String.format("updateCompany:%s", companyVo.toString()));
		if(null == companyVo.getId()) {
			throw new RuntimeException("updateCompany id can not is null!");
		}
		Company companyBo = new Company();
		BeanUtils.copyProperties(companyVo, companyBo);
		companyRepository.saveAndFlush(companyBo);
	}

	@SuppressWarnings("unused")
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDIT')")
	public void deleteCompany(Long id) {
		log.info(String.format("deleteCompany ID:%s", id.toString()));
		if(null == id) {
			throw new RuntimeException("deleteCompany id can not is null!");
		}
		companyRepository.deleteById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDIT') OR hasRole('SEARCH')")
	public List<CompanyDTO> findCompanyByName(String name) {
		log.info(String.format("findCompanyByName name:%s", name));
		if(StringUtils.isBlank(name)) {
			throw new RuntimeException("findCompanyByName name can not is null!");
		}
		List<Company> querys = companyRepository.findByName(name);
		List<CompanyDTO> retQuerys = new ArrayList<>();
		if (null != querys && querys.size() > 0) {
			for(Company bo : querys) {
				CompanyDTO vo = new CompanyDTO();
				BeanUtils.copyProperties(bo, vo);
				retQuerys.add(vo);
			}
		}
		return retQuerys;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDIT') OR hasRole('SEARCH')")
	public List<CompanyDTO> findCompanyAll() {
		
		List<Company> querys = companyRepository.findAll();
		List<CompanyDTO> retQuerys = new ArrayList<>();
		if (null != querys && querys.size() > 0) {
			for(Company bo : querys) {
				CompanyDTO vo = new CompanyDTO();
				BeanUtils.copyProperties(bo, vo);
				retQuerys.add(vo);
			}
		}
		return retQuerys;
	}
}
