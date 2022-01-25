package com.gada.root;




import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        List<Donation> recentDonations = donations.size() < 5 ? donations : donations.subList(donations.size()-5, donations.size());
        Collections.reverse(recentDonations);
        
        Integer donationsAmount = donations.size();
    
        model.addAttribute("donations" , donationsAmount );
        model.addAttribute("Donations", recentDonations);
        model.addAttribute("comment",new Comment());
        model.addAttribute("comments", this.cRepo.findByPostId(postId));
        
       
        return "fundraiser";}
        
   
   
        @PostMapping("/fundraiser/{postId}/comment")
        public String postController( @ModelAttribute("comment") Comment comment, @PathVariable Long postId,Model model){
            
            comment.setPost(this.repo.findPostById(postId));
            this.cRepo.save(comment);
            return "redirect:/fundraiser/{postId}";
        }
    
}
