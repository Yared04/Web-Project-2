package com.gada.root;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface CommentRepositary extends CrudRepository<Comment, String> {
 
    public List<Comment> findByPostId(Long postId);
}
