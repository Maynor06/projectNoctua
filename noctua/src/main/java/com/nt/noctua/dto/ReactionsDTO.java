package com.nt.noctua.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionsDTO {

    private Long id;
    private Long postId;
    private Long userId;
    private LocalDateTime dateCreate;
}
