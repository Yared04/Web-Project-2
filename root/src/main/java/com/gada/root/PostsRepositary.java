package com.gada.root;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface PostsRepositary extends CrudRepository<Posts, Long>  {
     
     Posts findPostById(Long id);
     List<Posts> findPostByTitle(String title);
}