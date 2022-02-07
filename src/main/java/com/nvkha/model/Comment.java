package com.nvkha.model;

import com.nvkha.repository.entity.PostEntity;

import java.time.LocalDate;

public class Comment {

    private Long id;
    private String content;
    private LocalDate createdDate;
    private PostEntity post;

    public Comment() {}

    public Comment(String content) {
        this.content = content;
    }

    public Comment(Long id, String content, LocalDate createdDate, PostEntity post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.post = post;
    }

    public Comment(String content, PostEntity post, LocalDate createdDate) {
        this.content = content;
        this.post = post;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }
}
