package com.gada.root;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepositary extends CrudRepository<Comment, String> {
 
    List<Comment> findByPostId(Long postId);
   
}
