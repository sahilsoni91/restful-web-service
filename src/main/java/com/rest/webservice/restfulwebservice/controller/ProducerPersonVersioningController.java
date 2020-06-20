package com.rest.webservice.restfulwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfulwebservice.bean.Name;
import com.rest.webservice.restfulwebservice.bean.PersonV1;
import com.rest.webservice.restfulwebservice.bean.PersonV2;

@RestController
public class ProducerPersonVersioningController {

	/*
	 * Known as Media Type, Content Negotiation, Accept Header Versioning
	 * Used by Github
	 * Cons:
	 * Misuse of HTTP Headers : Not meant for versioning
	 * Caching : Not possible
	 * Execute request on browser : no
	 * 
	 * Pros:
	 * URI Pollution : It not pollute the URL
	 * 
	 */
	
	@GetMapping(path = "person", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getPersonV1() {
		return new PersonV1("Sahil Soni");
	}
	
	@GetMapping(path = "person", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Sahil", "Soni"));
	}
}
