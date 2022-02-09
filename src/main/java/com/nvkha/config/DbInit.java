package com.nvkha.config;

import com.nvkha.repository.CommentRepository;
import com.nvkha.repository.PostRepository;
import com.nvkha.repository.UserRepository;
import com.nvkha.repository.entity.PostEntity;
import com.nvkha.repository.entity.UserEntity;
import com.nvkha.repository.entity.UserRole;
import com.nvkha.security.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DbInit {
    @Bean
    CommandLineRunner commandLineRunner(PostRepository postRepository,
                                        CommentRepository commentRepository,
                                        UserRepository userRepository,
                                        PasswordEncoder passwordEncoder) {
        return args -> {
            for(int i = 0; i < 10; i++) {
                PostEntity post = new PostEntity(
                        "Lorem Ipsum " + i,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec auctor ex nulla, sed efficitur ante tincidunt quis. Nunc nisl risus, ornare in eros sit amet, tristique varius lacus. Maecenas id ante sed augue condimentum ullamcorper. Integer eu eros tortor. Nulla vitae sodales turpis. Proin quis felis metus. Aliquam laoreet elit sed est lacinia, quis efficitur nulla porttitor. Pellentesque blandit faucibus commodo. Morbi et justo et felis malesuada efficitur non eu ligula. Suspendisse posuere ante leo, a porttitor nulla accumsan at. Aliquam condimentum neque eget viverra malesuada. Vestibulum sit amet neque interdum, ultrices libero at, egestas dui. Ut tristique porta tempus. Etiam viverra orci a erat euismod cursus. Morbi a nunc ante.",
                        LocalDate.of(1999, 1, 1),
                        LocalDate.of(1999, 1, 1)
                );
                postRepository.save(post);
            }
            UserEntity admin = new UserEntity("admin", passwordEncoder.bCryptPasswordEncoder().encode("12345"), UserRole.ADMIN);
            userRepository.save(admin);
        };
    }
}
