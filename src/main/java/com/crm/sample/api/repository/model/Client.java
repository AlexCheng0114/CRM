package com.crm.sample.api.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client {

	public Client() {
		super();
	}

	public Client(Long id, Long companyId, String name, String email, String phone) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "companyId")
	private Long companyId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
