package com.look.monkey.controller;

import com.look.monkey.entity.Customer;
import com.look.monkey.repository.CustomerRepository;
import com.look.monkey.repository.NamesOnly;
import com.look.monkey.repository.extend.CustomerExtendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/monkey/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerExtendRepository customerExtendRepository;
	
	
	
	@RequestMapping(value="/findAll",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAll(){
		return this.customerRepository.findAll();
	}
	
	@RequestMapping(value="/findAllExtend",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAllQueryDSL(){
		return this.customerExtendRepository.findAll();
	}
	@RequestMapping(value="/findByLastName/{lastName}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer findByLastName(
			@PathVariable("lastName") @NotNull String lastName
			){
		return this.customerExtendRepository.findByLastName(lastName);
	}
	
	@RequestMapping(value="/testProjects/{firstName}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<NamesOnly> testProjects(
			@PathVariable("firstName") @NotNull String firstName
			){
		return this.customerRepository.findByFirstName(firstName);
	}
}
