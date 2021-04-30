package com.crm.sample.api.controller.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO{

	private Long id;
	
	private Long companyId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
