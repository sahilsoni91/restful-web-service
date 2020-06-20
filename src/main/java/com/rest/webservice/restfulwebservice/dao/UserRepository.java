package com.rest.webservice.restfulwebservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservice.restfulwebservice.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
