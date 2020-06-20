package com.rest.webservice.restfulwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfulwebservice.bean.Name;
import com.rest.webservice.restfulwebservice.bean.PersonV1;
import com.rest.webservice.restfulwebservice.bean.PersonV2;

@RestController
public class HeaderPersonVersioningController {

	/*
	 * Used by Microsoft
	 * Cons:
	 * Misuse of HTTP Headers : Not meant for versioning
	 * Caching : Not possible
	 * Execute request on browser : no
	 * 
	 * Pros:
	 * URI Pollution : It not pollute the URL
	 * 
	 */
	
	@GetMapping(path = "person", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Sahil Soni");
	}
	
	@GetMapping(path = "person", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Sahil", "Soni"));
	}
}
