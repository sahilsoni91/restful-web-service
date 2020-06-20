package com.rest.webservice.restfulwebservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfulwebservice.bean.Filter;

@RestController
public class StaticFieldFilterController {

	@GetMapping(path = "filter")
	public Filter retrieveFilter() {
		return new Filter("data1", "data2", "data3");
	}
	
	@GetMapping(path = "filter-list")
	public List<Filter> retrieveFilterList() {
		return Arrays.asList(new Filter("data1", "data2", "data3"), new Filter("data11", "data22", "data33"));
	}
}
