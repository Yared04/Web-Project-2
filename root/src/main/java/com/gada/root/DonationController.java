package com.gada.root;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class DonationController {
    private final DonationRepository repo;
    private final PostsRepositary pRepo;

    @GetMapping("/fundraiser/{postId}/donate")
    public String donationForm(@PathVariable Long postId, Model model){
        Posts post = this.pRepo.findPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("donation", new Donation());
        return "donationForm";
    }
    @PostMapping("/fundraiser/{postId}/donate")
    public String processDonation(@ModelAttribute("donation") @Valid Donation donation, Errors errors, @PathVariable Long postId, Model model){
           

            if(errors.hasErrors()){
                Posts post = this.pRepo.findPostById(postId);
                model.addAttribute("post", post);
                return "donationForm";
            }
            
            LocalDateTime ldt = LocalDateTime.now();
            Posts post = this.pRepo.findPostById(postId);
            donation.setDate(ldt);
            donation.setPost(post);

            
            this.repo.save(donation);
        return "thankYou";
    }
}
