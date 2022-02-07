package com.nvkha.service;

import com.nvkha.model.Post;
import com.nvkha.repository.entity.PostEntity;

import java.time.LocalDate;
import java.util.List;

public interface IPostService {
    List<Post> getAllPost();

    void addNewPost(Post post);

    void deletePost(Long postId);

    void updatePost(Long postId, String title, String content, LocalDate modifiedDate);
}
