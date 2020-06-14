package com.rest.webservice.restfulwebservice.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.webservice.restfulwebservice.bean.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;
	
	static {
		++userCount;
		users.add(new User(userCount, "Sahil", LocalDate.now()));
		++userCount;
		users.add(new User(userCount, "Sandeep", LocalDate.now()));
		++userCount;
		users.add(new User(userCount, "Santosh", LocalDate.now()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
}
