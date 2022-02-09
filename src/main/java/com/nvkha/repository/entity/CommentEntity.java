package com.nvkha.repository.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class CommentEntity {
    @Id
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )

    @Column(
            name = "id",
            nullable = false
    )
    private Long id;
    @Column(
            name = "content",
            nullable = false
    )
    private String content;
    @Column(
            name = "created_date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private LocalDate createdDate;

    @ManyToOne()
    @JoinColumn(
            name="post_id",
            nullable=false,
            foreignKey = @ForeignKey(name = "FK_comment_post")
    )
    private PostEntity post;

    public CommentEntity() {}

    public CommentEntity(Long id, String content, LocalDate createdDate, PostEntity post) {
        this.id = id;
        this.content = content;
        this.createdDate = createdDate;
        this.post = post;
    }

    public CommentEntity(String content, PostEntity post, LocalDate createdDate) {
        this.content = content;
        this.post = post;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                ", post=" + post +
                '}';
    }
}
