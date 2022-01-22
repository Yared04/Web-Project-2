package com.gada.root;




import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FundraiserController {
    @Autowired
    private PostsRepositary repo;
    @Autowired
    private DonationRepository dRepo;
    @Autowired
    private final CommentRepositary cRepo;

    @GetMapping("/fundraiser/{postId}")
    public String showFundraiser(@PathVariable Long postId, Model model){
        Posts posts = this.repo.findPostById(postId);
        model.addAttribute("post", posts);
        List<Donation> donations = this.dRepo.findDonationByPostId(posts);
        
        Integer donationsAmount = donations.size();
    
        model.addAttribute("donations" , donationsAmount );
        model.addAttribute("Donations", donations);
        model.addAttribute("com",new Comment());
        List<Comment> comments = new ArrayList<>();
        cRepo.findByPostId(posts.getId()).forEach(i -> comments.add(i));

        model.addAttribute("comments", comments);
        
       
        return "fundraiser";}
        
   
   
        @PostMapping("/fundraiser/{postId}")
        public String processComment(@ModelAttribute("comment") Comment comment ,@PathVariable Long postId, Model model,BindingResult bindingResult){
            comment.setPostId(postId);
            if (bindingResult.hasErrors()){
                log.info("has errrrror");
                return "fundraiser";
            }
            this.cRepo.save(comment);
    
            return "redirect:/fundraiser/{postId}";
        }
    
}
