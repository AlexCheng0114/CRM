package com.crm.sample.api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.sample.api.controller.vo.ClientDTO;
import com.crm.sample.api.service.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/client")
@Api(tags = {"client api"}, value = "client")
@SwaggerDefinition(tags = {
	@Tag(name = "client api", description = "apis for client")
})
public class ClientController {
	
	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "add a client data.")
	@RequestMapping(
		value = "/add",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> addClient(@Valid @RequestBody ClientDTO client)  {
		log.info("Enter into addClient controller");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		log.info(securityContext.getAuthentication().getAuthorities().toString());
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			clientService.addClient(client);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("addClient AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("addClient Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "update a client data.")
	@RequestMapping(
		value = "/update",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> updateClient(@Valid @RequestBody ClientDTO client)  {
		log.info("Enter into updateClient controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			clientService.updateClient(client);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("updateClient AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("updateClient Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "delete the client data by clientId")
	@RequestMapping(
		value = "/delete",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> deleteClientById(@Valid @RequestBody Long clientId) {
		log.info("Enter into deleteUserById controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			clientService.deleteClient(clientId);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("deleteClientById AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("deleteClientById Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "find the client data by name")
	@RequestMapping(
		value = "/search",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> findClientByName(@Valid @RequestBody String name) {
		log.info("Enter into findClientByName controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<ClientDTO> retquerys = clientService.findClientByName(name);
			result.put("Clients", retquerys);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("findClientByName AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("findClientByName Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "find all client data")
	@RequestMapping(
		value = "/searchAll",
		method = RequestMethod.POST,
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> findClientAll() {
		log.info("Enter into findClientAll controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<ClientDTO> retquerys = clientService.findClientAll();
			result.put("Clients", retquerys);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("findClientAll AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("findClientAll Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "add clients data.")
	@RequestMapping(
		value = "/adds",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> addClients(@Valid @RequestBody List<ClientDTO> clients)  {
		log.info("Enter into addClients controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			clientService.addClients(clients);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("addClients AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("addClients Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}

}