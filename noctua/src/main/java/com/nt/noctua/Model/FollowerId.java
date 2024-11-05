package com.nt.noctua.Model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowerId implements Serializable {

    private int userId;
    private int followerId;

    public FollowerId() {}

    public FollowerId(int userId, int followerId) {
        this.userId = userId;
        this.followerId = followerId;
    }

    // Getters y setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    // Sobrescribimos equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FollowerId that = (FollowerId) o;
        return userId == that.userId && followerId == that.followerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, followerId);
    }
}

