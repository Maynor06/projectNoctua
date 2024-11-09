package com.nt.noctua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDTO {

    private Long userId;
    private String content;
    private String urlImage;
}
