package com.gada.root;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FundraiserController {
    @Autowired
    private PostsRepositary repo;
    @Autowired
    private DonationRepository dRepo;

    @GetMapping("/fundraiser/{postId}")
    public String showFundraiser(@PathVariable Long postId, Model model){
        Posts posts = this.repo.findPostById(postId);
        model.addAttribute("post", posts);
        List<Donation> donations = this.dRepo.findDonationByPostId(posts);
        Double total = 0.0;
        Integer donationsAmount = donations.size();
        for (Donation d: donations) {
            total += d.getAmount();
        }
        model.addAttribute("totalAmount" , total );
        model.addAttribute("donations" , donationsAmount );
        
       
        return "fundraiser";
    }
}
