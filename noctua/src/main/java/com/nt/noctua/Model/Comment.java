package com.nt.noctua.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ModelUser user;

    private String content;

    @Column(name = "date_create", nullable = false, updatable = false)
    private LocalDateTime date_comment = LocalDateTime.now();

    public Comment(Posts posts, ModelUser user, String content) {
            this.posts = posts;
            this.user = user;
            this.content = content;
            this.date_comment = LocalDateTime.now();
    }
}
