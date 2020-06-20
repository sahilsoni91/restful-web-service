package com.rest.webservice.restfulwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfulwebservice.bean.Name;
import com.rest.webservice.restfulwebservice.bean.PersonV1;
import com.rest.webservice.restfulwebservice.bean.PersonV2;

@RestController
public class ReqParamPersonVersioningController {

	/*
	 * Used by Amazon
	 * Cons:
	 * URI Pollution : It Pollute the URL
	 * 
	 * Pros:
	 * Misuse of HTTP Headers : Not use HTTP headers
	 * Caching : possible
	 * Execute request on browser : yes
	 * 
	 */
	
	@GetMapping(path = "person", params = "ver=1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Sahil Soni");
	}
	
	@GetMapping(path = "person", params = "ver=2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Sahil", "Soni"));
	}
}
