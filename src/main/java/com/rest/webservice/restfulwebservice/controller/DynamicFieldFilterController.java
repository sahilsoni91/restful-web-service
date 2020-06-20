package com.rest.webservice.restfulwebservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservice.restfulwebservice.bean.Filter;
import com.rest.webservice.restfulwebservice.util.Utility;

@RestController
public class DynamicFieldFilterController {

	@GetMapping(path = "dyn-filter")
	public MappingJacksonValue retrieveFilter() {
		Filter f = new Filter("data1", "data2", "data3");
		String[] filterFields = new String[] {"field1", "field2"};
		MappingJacksonValue mapping = new MappingJacksonValue(f);
		FilterProvider filters = Utility.getFilter(filterFields);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping(path = "dyn-filter-list")
	public MappingJacksonValue retrieveFilterList() {
		List<Filter> list = Arrays.asList(new Filter("data1", "data2", "data3"), new Filter("data11", "data22", "data33"));
		String[] filterFields = new String[] {"field2", "field3"};
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		FilterProvider filters = Utility.getFilter(filterFields);
		mapping.setFilters(filters);
		return mapping;
	}
}
