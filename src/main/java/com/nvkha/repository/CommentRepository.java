package com.nvkha.repository;

import com.nvkha.repository.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    @Query(value = "SELECT c FROM CommentEntity c WHERE c.post.id = ?1")
    List<CommentEntity> findAllByPost(Long id);
}
