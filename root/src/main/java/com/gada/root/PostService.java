package com.gada.root;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    
    @Autowired
    private PostsRepositary postRepo;

  
    public List<Posts> listAll(){
        return (List<Posts>) postRepo.findAll();
        
    }
    public Posts get(Long id) {
        return postRepo.findById(id).get();
    }
    public void save(Posts post) {
        postRepo.save(post);
    }
}
