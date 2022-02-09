package com.nvkha.service;

import com.nvkha.model.Post;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public interface IPostService {
    Page<Post> getAllPost(Integer page, String sortBy);

    void addNewPost(Post post);

    void deletePost(Long postId);

    void updatePost(Long postId, String title, String content, LocalDate modifiedDate);

    Post getPostById(Long postId);
}
