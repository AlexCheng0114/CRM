package com.crm.sample.api.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crm.sample.api.controller.vo.ClientDTO;
import com.crm.sample.api.controller.vo.CompanyDTO;
import com.crm.sample.api.service.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/company")
@Api(tags = {"company api"}, value = "company")
@SwaggerDefinition(tags = {
	@Tag(name = "company api", description = "apis for company")
})
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	@ApiOperation(value = "add a company data.")
	@RequestMapping(
		value = "/add",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> addCompany(@Valid @RequestBody CompanyDTO companyVo)  {
		log.info("Enter into addCompany controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			companyService.addCompany(companyVo);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("addCompany AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("addCompany Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "update a company data.")
	@RequestMapping(
		value = "/update",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> updateCompany(@Valid @RequestBody CompanyDTO companyVo)  {
		log.info("Enter into updateCompany controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			companyService.updateCompany(companyVo);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("updateCompany AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("updateCompany Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}

	@ApiOperation(value = "delete the company data by clientId")
	@RequestMapping(
		value = "/{companyId}",
		method = RequestMethod.DELETE,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> deleteCompanyById(@PathVariable("companyId") Long companyId) {
		log.info("Enter into deleteCompanyById controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			companyService.deleteCompany(companyId);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("deleteCompanyById AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("deleteCompanyById Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "find the company data by name")
	@RequestMapping(
		value = "/search",
		method = RequestMethod.POST,
		consumes = { MediaType.APPLICATION_JSON_VALUE },
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> findCompanyByName(@Valid @RequestBody String name) {
		log.info("Enter into findCompanyByName controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<CompanyDTO> retquerys = companyService.findCompanyByName(name);
			result.put("Companys", retquerys);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("findCompanyByName AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("findCompanyByName Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}
	
	@ApiOperation(value = "find all company data")
	@RequestMapping(
		value = "/searchAll",
		method = RequestMethod.POST,
		produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public Map<String, Object> findCompanyAll() {
		log.info("Enter into findCompanyAll controller");
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<CompanyDTO> retquerys = companyService.findCompanyAll();
			result.put("Companys", retquerys);
			result.put("message", "SUCCESS");
		} catch (AccessDeniedException e) {
			log.error("findCompanyAll AccessDeniedException!", e);
			result.put("message", e.getMessage());
		} catch (Exception e) {
			log.error("findCompanyAll Exception!", e);
			result.put("message", e.getMessage());
		}
		return result;
	}

}