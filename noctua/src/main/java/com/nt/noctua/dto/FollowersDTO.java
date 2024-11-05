package com.nt.noctua.dto;

public class FollowersDTO {

    private Long userId;
    private Long folowerId;

    public FollowersDTO(Long userId, Long folowerId) {
        this.userId = userId;
        this.folowerId = folowerId;
    }
}
