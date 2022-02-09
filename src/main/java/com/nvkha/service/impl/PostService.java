package com.nvkha.service.impl;

import com.nvkha.model.Post;
import com.nvkha.repository.PostRepository;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.service.IPostService;
import com.nvkha.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<Post> getAllPost(Integer page, String sortBy) {
        Page<PostEntity> postEntities = postRepository.findAll(PageRequest.of(page, 5));
        Page<Post> posts = postEntities.map(new Function<PostEntity, Post>() {
            @Override
            public Post apply(PostEntity postEntity) {
                Post newPost = new Post();
                newPost.setId(postEntity.getId());
                newPost.setCreatedDate(postEntity.getCreatedDate());
                newPost.setModifiedDate(postEntity.getModifiedDate());
                newPost.setTitle(postEntity.getTitle());
                newPost.setContent(postEntity.getContent());
                return newPost;
            }
        });
        return posts;
    }

    @Override
    public void addNewPost(Post post) {
        PostEntity newPostEntity = new PostEntity();
        newPostEntity.setTitle(post.getTitle());
        newPostEntity.setContent(post.getContent());
        newPostEntity.setCreatedDate(post.getCreatedDate());
        newPostEntity.setModifiedDate(post.getModifiedDate());
        postRepository.save(newPostEntity);
    }

    @Override
    public void deletePost(Long postId) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(!optionalPostEntity.isPresent()) {
            throw new IllegalStateException("Post is not exists");
        }
        postRepository.deleteById(postId);
    }

    @Override
    @Transactional
    public void updatePost(Long postId, String title, String content, LocalDate modifiedDate) {
        Optional<PostEntity> optionalPostEntity = postRepository.findById(postId);
        if(!optionalPostEntity.isPresent()) {
            throw new IllegalStateException("Post id not exists");
        }
        PostEntity postEntity = optionalPostEntity.get();
        if(!StringUtils.isNullOrEmpty(title)) {
            postEntity.setTitle(title);
        }
        if(!StringUtils.isNullOrEmpty(content)) {
            postEntity.setContent(content);
        }
        if(modifiedDate != null) {
            postEntity.setModifiedDate(modifiedDate);
        }
    }

    @Override
    public Post getPostById(Long postId) {
        Optional<PostEntity> optionalPost = postRepository.findById(postId);
        if(!optionalPost.isPresent()) {
            throw new IllegalStateException("Post not exits");
        }
        Post post = new Post();
        post.setId(optionalPost.get().getId());
        post.setTitle(optionalPost.get().getTitle());
        post.setContent(optionalPost.get().getContent());
        post.setCreatedDate(optionalPost.get().getCreatedDate());
        post.setModifiedDate(optionalPost.get().getModifiedDate());
        return post;
    }
}
