package com.crm.sample.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.crm.sample.api.controller.vo.ClientDTO;
import com.crm.sample.api.repository.ClientRepository;
import com.crm.sample.api.repository.model.Client;
import com.crm.sample.api.service.ClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public void addClient(ClientDTO clientVo) {
		log.info(String.format("addClient:%s", clientVo.toString()));
		Client clientBo = new Client();
		BeanUtils.copyProperties(clientVo, clientBo);
		clientBo.setId(null);
		clientRepository.saveAndFlush(clientBo);
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
	public void addClients(List<ClientDTO> clientVos) {
		log.info(String.format("addClients:%s", clientVos.toString()));
		for(ClientDTO clientVo : clientVos) {
			addClient(clientVo);
		}
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	public void updateClient(ClientDTO clientVo) {
		log.info(String.format("updateClient:%s", clientVo.toString()));
		if(null == clientVo.getId()) {
			throw new RuntimeException("updateClient id can not is null!");
		}
		Client clientBo = new Client();
		BeanUtils.copyProperties(clientVo, clientBo);
		clientRepository.saveAndFlush(clientBo);
	}

	@SuppressWarnings("unused")
	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER')")
	public void deleteClient(Long id) {
		log.info(String.format("deleteClient ID:%s", id.toString()));
		if(null == id) {
			throw new RuntimeException("deleteClient id can not is null!");
		}
		clientRepository.deleteById(id);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER') OR hasRole('USER')")
	public List<ClientDTO> findClientByName(String name) {
		log.info(String.format("findClientByName name:%s", name));
		if(StringUtils.isBlank(name)) {
			throw new RuntimeException("findClientByName name can not is null!");
		}
		List<Client> querys = clientRepository.findByName(name);
		List<ClientDTO> retQuerys = new ArrayList<>();
		if (null != querys && querys.size() > 0) {
			for(Client bo : querys) {
				ClientDTO vo = new ClientDTO();
				BeanUtils.copyProperties(bo, vo);
				retQuerys.add(vo);
			}
		}
		return retQuerys;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MANAGER') OR hasRole('USER')")
	public List<ClientDTO> findClientAll() {
		
		List<Client> querys = clientRepository.findAll();
		List<ClientDTO> retQuerys = new ArrayList<>();
		if (null != querys && querys.size() > 0) {
			for(Client bo : querys) {
				ClientDTO vo = new ClientDTO();
				BeanUtils.copyProperties(bo, vo);
				retQuerys.add(vo);
			}
		}
		return retQuerys;
	}
}
