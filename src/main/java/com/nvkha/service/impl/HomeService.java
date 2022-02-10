package com.nvkha.service.impl;

import com.nvkha.model.Comment;
import com.nvkha.model.PaginatedResponse;
import com.nvkha.model.Post;
import com.nvkha.service.IHomeService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class HomeService implements IHomeService {
    final String URI_POSTS = "https://nvkha-blog.herokuapp.com/api/v1/posts";
    final String URI_COMMENTS = "https://nvkha-blog.herokuapp.com/api/v1/comments";

    @Override
    public ResponseEntity<PaginatedResponse<Post>> callGetAllPostApi(Integer page) {
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<PaginatedResponse<Post>> responseType = new ParameterizedTypeReference<PaginatedResponse<Post>>() { };

        ResponseEntity<PaginatedResponse<Post>> result = restTemplate.exchange(URI_POSTS + "?page=" + page,
                HttpMethod.GET, null/*httpEntity*/, responseType);

        return result;
    }

    @Override
    public Post callGetPostByIdApi(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Post result = restTemplate.getForObject(URI_POSTS + "/" + id, Post.class);
        return result;
    }

    @Override
    public List<Comment> callGetAllCommentByPostApi(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Comment[] result = restTemplate.getForObject(URI_COMMENTS + "?postId=" + id, Comment[].class);
        return Arrays.asList(result);
    }
}
