package com.nt.noctua.dto;

import com.nt.noctua.Model.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;
    private Long userId;
    private String content;
    private String urlImage;
    private LocalDateTime createdAt;

    public PostDTO(Posts post) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.content = post.getContent();
        this.createdAt = post.getDate_create();
        this.urlImage = post.getUrlImage();
    }
}
