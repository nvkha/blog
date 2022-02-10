package com.nvkha.controller;

import com.nvkha.model.PaginatedResponse;
import com.nvkha.model.Post;
import com.nvkha.model.User;
import com.nvkha.service.impl.CommentService;
import com.nvkha.service.impl.HomeService;
import com.nvkha.service.impl.PostService;
import com.nvkha.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final HomeService homeService;
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public AdminController(HomeService homeService, PostService postService, UserService userService, CommentService commentService) {
        this.homeService = homeService;
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/manage-posts")
    public String managePost(@RequestParam Optional<Integer> page,
                             @RequestParam Optional<String> message,
                            @RequestParam Optional<String> type,Model model) {
        ResponseEntity<PaginatedResponse<Post>> result = homeService.callGetAllPostApi(page.orElse(0));
        List<Post> posts = result.getBody().getContent();
        PaginatedResponse<Post> pageInfo = result.getBody();

        model.addAttribute("title", "Manage Posts");
        model.addAttribute("posts", posts);
        model.addAttribute("page", pageInfo);

        if(message.isPresent() && type.isPresent()) {
            model.addAttribute("message", message.get());
            model.addAttribute("type", type.get());
        }
        return "admin/manage-posts";
    }

    @GetMapping("/manage-posts/create")
    public String add() {
        return "admin/add-post";
    }

    @GetMapping("/manage-posts/edit/{postId}")
    public String edit(@PathVariable Long postId,
            @RequestParam Optional<Boolean> isSuccess
            ,Model model) {
        Post post = postService.getPostById(postId.longValue());
        model.addAttribute("post", post);
        if(isSuccess.isPresent()) {
            if(isSuccess.get()) {
                model.addAttribute("message", "Edit post success");
                model.addAttribute("type", "success");
            }
            else {
                model.addAttribute("message", "Error");
                model.addAttribute("type", "danger");
            }
        }
        return "admin/edit-post";
    }

    @PostMapping("/manage-posts/delete/{postId}")
    public String delete(@PathVariable Long postId, RedirectAttributes redirAttrs) {
        commentService.deleteAllCommentByPost(postId);
        postService.deletePost(postId.longValue());
        redirAttrs.addFlashAttribute("message", "Delete post success");
        redirAttrs.addFlashAttribute("type", "success");
        return "redirect:/admin/manage-posts";
    }

    // Manage users
    @GetMapping("/manage-users")
    public String manageUser(@RequestParam Optional<Integer> page, Model model) {
        List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
        return "admin/manage-users";
    }

    @PostMapping("/manage-users/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/manage-users";
    }
}
