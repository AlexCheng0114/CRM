package com.crm.sample.api.controller.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

	private Long id;
	private String name;
	private String address;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
