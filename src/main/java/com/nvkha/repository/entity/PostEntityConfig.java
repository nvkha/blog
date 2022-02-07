package com.nvkha.repository.entity;

import com.nvkha.model.Post;
import com.nvkha.repository.CommentRepository;
import com.nvkha.repository.PostRepository;
import javafx.geometry.Pos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Optional;

@Configuration
public class PostEntityConfig {
    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository,
                                        CommentRepository commentRepository) {
        return args -> {
            PostEntity post1 = new PostEntity(
                    "example",
                    "example",
                    LocalDate.of(1999, 1, 1),
                    LocalDate.of(1999, 1, 1)
            );
            PostEntity post2 = new PostEntity(
                    "example",
                    "example",
                    LocalDate.of(1999, 1, 1),
                    LocalDate.of(1999, 1, 1)
            );
            postRepository.save(post1);
            postRepository.save(post2);
        };
    }
}
