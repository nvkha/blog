package com.nvkha.controller;

import com.nvkha.model.Comment;
import com.nvkha.model.PaginatedResponse;
import com.nvkha.model.Post;
import com.nvkha.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    private final IHomeService homeService;

    @Autowired
    public HomeController(IHomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public String home(@RequestParam Optional<Integer> page,
                       Model model) {
        ResponseEntity<PaginatedResponse<Post>> result = homeService.callGetAllPostApi(page.orElse(0));
        List<Post> posts = result.getBody().getContent();
        PaginatedResponse<Post> pageInfo = result.getBody();

        model.addAttribute("title", "Blog");
        model.addAttribute("posts", posts);
        model.addAttribute("page", pageInfo);
        return "/index";
    }

    @GetMapping({"/posts/{id}"})
    public String post(@PathVariable Long id, Model model) {
        Post post = homeService.callGetPostByIdApi(id.longValue());
        List<Comment> comments = homeService.callGetAllCommentByPostApi(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "/post";
    }
}
