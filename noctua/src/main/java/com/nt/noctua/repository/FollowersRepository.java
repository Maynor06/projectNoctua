package com.nt.noctua.repository;

import com.nt.noctua.Model.FollowerId;
import com.nt.noctua.Model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, FollowerId>{

    List<Followers> findById_UserId(Long userId);

    boolean existsById_UserIdAndId_FollowerId(int userId, int followerId);

    void deleteById_UserIdAndId_FollowerId(int userId, int followerId);

    @Query("SELECT f.id.followerId FROM Followers f WHERE f.id.userId = :userId")
    List<Integer> findFollowersByUserId(@Param("userId") int userId);

    void deleteById(FollowerId id);
}

