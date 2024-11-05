package com.nt.noctua.dto;

import com.nt.noctua.Model.ModelUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private String bio;

    private String profileImagenUrl;

    public UserDTO(ModelUser user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        this.bio = user.getBio();
        this.profileImagenUrl = user.getProfileImagenUrl();
    }
}
