package com.nt.noctua.repository;

import com.nt.noctua.Model.Posts;
import com.nt.noctua.Model.Reactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {

    List<Posts> findByUserId(Long userId);

}
