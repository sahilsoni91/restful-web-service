package com.rest.webservice.restfulwebservice.util;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class Utility {

	public static FilterProvider getFilter(String[] filterFields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filterFields);
		FilterProvider filters = new SimpleFilterProvider().addFilter("filterBeanFilter", filter);
		return filters;
	}
}
