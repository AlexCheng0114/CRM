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
import com.crm.sample.api.repository.ClientRepository;
import com.crm.sample.api.repository.model.Client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
public class ClientServiceTest {

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void addClient() {
		Client bo = new Client();
		bo.setCompanyId(Long.getLong("1"));
		bo.setName("testName1");
		bo.setEmail("Email1");
		bo.setPhone("phone1");
		clientRepository.saveAndFlush(bo);
		List<Client> datas = clientRepository.findByName("testName1");
		assertThat(datas.size(), is(1));
		log.info(datas.toString());
	}
	
	@Test
	public void editClient() {
		Client bo = new Client();
		bo.setCompanyId(Long.getLong("1"));
		bo.setName("testName1");
		bo.setEmail("Email1");
		bo.setPhone("phone1");
		clientRepository.saveAndFlush(bo);
		Optional<Client> srcBo = clientRepository.findById(1L);
		
		srcBo.get().setName("test2");
		clientRepository.saveAndFlush(srcBo.get());
		Optional<Client> checkBo = clientRepository.findById(1L);
		assertNotEquals(bo.getName(), checkBo.get().getName());
	}
	
	@Test
	public void deleteClient() {
		Client bo = new Client();
		bo.setCompanyId(Long.getLong("1"));
		bo.setName("testName1");
		bo.setEmail("Email1");
		bo.setPhone("phone1");
		clientRepository.saveAndFlush(bo);
		List<Client> datas = clientRepository.findAll();
		assertThat(datas.size(), is(1));
		clientRepository.delete(datas.get(0));
		datas = clientRepository.findAll();
		assertThat(datas.size(), is(0));
	}
	
//	@Test
	public void findClient() {
		Client bo = new Client();
		bo.setCompanyId(Long.getLong("1"));
		bo.setName("testName1");
		bo.setEmail("Email1");
		bo.setPhone("phone1");
		clientRepository.saveAndFlush(bo);
		List<Client> datas = clientRepository.findAll();
		assertThat(datas.size(), is(1));
	}
}
