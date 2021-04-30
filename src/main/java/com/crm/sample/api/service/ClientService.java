package com.crm.sample.api.service;

import java.util.List;

import com.crm.sample.api.controller.vo.ClientDTO;

public interface ClientService {
	public void updateClient(ClientDTO clientVo);
	public void deleteClient(Long id);
	public void addClient(ClientDTO clientVo);
	public List<ClientDTO> findClientByName(String name);
	public List<ClientDTO> findClientAll();
	
}
