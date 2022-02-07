package com.nvkha.controller;

import com.nvkha.model.Comment;
import com.nvkha.service.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
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

    @DeleteMapping(path = "{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
