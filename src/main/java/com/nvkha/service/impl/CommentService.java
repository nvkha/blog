package com.nvkha.service.impl;

import com.nvkha.model.Comment;
import com.nvkha.repository.CommentRepository;
import com.nvkha.repository.PostRepository;
import com.nvkha.repository.entity.CommentEntity;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.service.ICommentService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void addNewComment(Comment comment, Long postId) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(!optionalPostEntity.isPresent()) {
            throw new IllegalIdentifierException("Post not exists");
        }
        PostEntity postEntity = optionalPostEntity.get();
        CommentEntity newCommentEntity = new CommentEntity();
        newCommentEntity.setCreatedDate(comment.getCreatedDate());
        newCommentEntity.setContent(comment.getContent());
        newCommentEntity.setPost(postEntity);
        commentRepository.save(newCommentEntity);
    }

    @Override
    @Transactional
    public void updateComment(Long commentId, String content) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(commentId);
        if(!optionalCommentEntity.isPresent()) {
            throw new IllegalIdentifierException("Comment is not exists");
        }
        CommentEntity commentEntity = optionalCommentEntity.get();
        commentEntity.setContent(content);
    }

    @Override
    public void deleteAllCommentByPost(Long postId) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(!optionalPostEntity.isPresent()) {
            throw new IllegalIdentifierException("Post not exists");
        }
        commentRepository.deleteAllByPost(postId);
    }

    @Override
    public List<Comment> getAllCommentByPost(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findAllByPost(postId.longValue());
        System.out.println(commentEntities);
        List<Comment> comments = new ArrayList<>();
        for(CommentEntity commentEntity : commentEntities) {
            Comment newComment = new Comment();
            newComment.setId(commentEntity.getId());
            newComment.setPost(commentEntity.getPost());
            newComment.setContent(commentEntity.getContent());
            newComment.setCreatedDate(commentEntity.getCreatedDate());
            comments.add(newComment);
        }
        return comments;
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = new ArrayList<>();
        List<CommentEntity> commentEntities = commentRepository.findAll();
        for(CommentEntity commentEntity : commentEntities) {
            Comment newComment = new Comment();
            newComment.setId(commentEntity.getId());
            newComment.setContent(commentEntity.getContent());
            newComment.setCreatedDate(commentEntity.getCreatedDate());
            newComment.setPost(commentEntity.getPost());
            comments.add(newComment);
        }
        return comments;
    }


}
