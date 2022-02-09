package com.nvkha.controller;

import com.nvkha.model.Post;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/posts")
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<Post> getAllPost(
            @RequestParam Optional<Integer> page,
            @RequestParam Optional<String> sortBy
            ) {
        return postService.getAllPost(page.orElse(0), sortBy.orElse("id"));
    }

    @GetMapping(path = "{postId}")
    public Post getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return post;
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
