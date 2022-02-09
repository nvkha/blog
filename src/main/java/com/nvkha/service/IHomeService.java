package com.nvkha.service;

import com.nvkha.model.Comment;
import com.nvkha.model.PaginatedResponse;
import com.nvkha.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IHomeService {
    ResponseEntity<PaginatedResponse<Post>> callGetAllPostApi(Integer page);
    Post callGetPostByIdApi(Long id);

    List<Comment> callGetAllCommentByPostApi(Long id);
}
