package com.nvkha.controller;

import com.nvkha.model.Post;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/post")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping
    public void addNewPost(@RequestBody Post post) {
        LocalDate localDate = LocalDate.now();
        post.setCreatedDate(localDate);
        post.setModifiedDate(localDate);
        postService.addNewPost(post);
    }

    @DeleteMapping(path = "{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @PutMapping(path = "{postId}")
    public void updatePost(@PathVariable Long postId,
                           @RequestParam String title,
                           @RequestParam String content) {
        LocalDate modifiedDate = LocalDate.now();
        postService.updatePost(postId, title, content, modifiedDate);
    }

}
