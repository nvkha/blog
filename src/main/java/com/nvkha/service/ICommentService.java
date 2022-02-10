package com.nvkha.service;

import com.nvkha.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAllComment();

    void addNewComment(Comment comment, Long postId);

    void updateComment(Long commentId, String content);

    void deleteAllCommentByPost(Long postId);

    List<Comment> getAllCommentByPost(Long postId);
}
