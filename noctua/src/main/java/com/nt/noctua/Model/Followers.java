package com.nt.noctua.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "followers")
public class Followers {

    @EmbeddedId
    private FollowerId id;

    @Column(name = "date_create", nullable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    public Followers() {}

    public Followers(int userId, int followerId) {
        this.id = new FollowerId(userId, followerId);
        this.dateCreated = LocalDateTime.now();
    }

    // Getters y setters
    public FollowerId getId() {
        return id;
    }

    public void setId(FollowerId id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}

