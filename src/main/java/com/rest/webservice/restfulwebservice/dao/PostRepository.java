package com.rest.webservice.restfulwebservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservice.restfulwebservice.bean.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
