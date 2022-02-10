package com.nvkha.controller;

import com.nvkha.model.Comment;
import com.nvkha.repository.entity.CommentEntity;
import com.nvkha.service.ICommentService;
import com.nvkha.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/comments")
public class CommentController {
    private final ICommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public List<Comment> getAllCommentByPost(@RequestParam Long postId) {
        return commentService.getAllCommentByPost(postId);
    }

    @PostMapping
    public void addNewComment(@RequestBody Comment comment,
                              @RequestParam Long postId) {
        comment.setCreatedDate(LocalDate.now());
        commentService.addNewComment(comment, postId.longValue());
    }

    @PutMapping(path = "{commentId}")
    public void updateComment(@PathVariable Long commentId,
                              @RequestParam String content) {
        commentService.updateComment(commentId, content);
    }

    @DeleteMapping(path = "{postId}")
    public void deleteAllCommentByPost(@PathVariable Long postId) {
        commentService.deleteAllCommentByPost(postId);
    }
}
