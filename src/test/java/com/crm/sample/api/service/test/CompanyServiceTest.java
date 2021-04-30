package com.crm.sample.api.service.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crm.sample.api.CrmApplication;
import com.crm.sample.api.repository.CompanyRepository;
import com.crm.sample.api.repository.model.Client;
import com.crm.sample.api.repository.model.Company;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
public class CompanyServiceTest {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Test
	public void addClient() {
		Company bo = new Company();
		bo.setName("testName1");
		bo.setAddress("address1");
		companyRepository.saveAndFlush(bo);
		List<Company> datas = companyRepository.findByName("testName1");
		assertThat(datas.size(), is(1));
		log.info(datas.toString());
	}
	
	@Test
	public void editClient() {
		Company bo = new Company();
		bo.setName("testName1");
		bo.setAddress("address1");
		companyRepository.saveAndFlush(bo);
		List<Company> srcBos = companyRepository.findByName("testName1");
		
		srcBos.get(0).setName("test2");
		companyRepository.saveAndFlush(srcBos.get(0));
		List<Company> checkBos = companyRepository.findByName("test2");
		assertNotEquals(bo.getName(), checkBos.get(0).getName());
	}
	
	@Test
	public void deleteClient() {
		Company bo = new Company();
		bo.setName("testName1");
		bo.setAddress("address1");
		companyRepository.saveAndFlush(bo);
		List<Company> datas = companyRepository.findAll();
		assertThat(datas.size(), is(1));
		companyRepository.delete(datas.get(0));
		datas = companyRepository.findAll();
		assertThat(datas.size(), is(0));
	}
	
//	@Test
	public void findClient() {
		Company bo = new Company();
		bo.setName("testName1");
		bo.setAddress("address1");
		companyRepository.saveAndFlush(bo);
		List<Company> datas = companyRepository.findAll();
		assertThat(datas.size(), is(1));
	}
}
