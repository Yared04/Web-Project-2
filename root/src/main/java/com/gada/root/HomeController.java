package com.gada.root;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostsRepositary postRepo;
    @GetMapping("/")

    public String homePage(Model model){
        List<Posts> posts = new ArrayList<>();
        this.postRepo.findAll().forEach(i -> posts.add(i) );

            model.addAttribute("titles", posts);

        return "home";
    }
}
