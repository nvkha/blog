package com.nvkha.service.impl;

import com.nvkha.model.Post;
import com.nvkha.repository.PostRepository;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.service.IPostService;
import com.nvkha.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = new ArrayList<>();
        List<PostEntity> postEntities = postRepository.findAll();
        for(PostEntity postEntity : postEntities) {
            Post newPost = new Post();
            newPost.setId(postEntity.getId());
            newPost.setTitle(postEntity.getTitle());
            newPost.setContent(postEntity.getContent());
            newPost.setCreatedDate(postEntity.getCreatedDate());
            newPost.setModifiedDate(postEntity.getModifiedDate());
            posts.add(newPost);
        }
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
}
