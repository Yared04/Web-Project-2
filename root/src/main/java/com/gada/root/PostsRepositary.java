package com.gada.root;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepositary extends JpaRepository<Posts,Long>  {
   
    public Optional<Posts> findById(Long L);  


}
