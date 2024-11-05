package com.nt.noctua.repository;

import com.nt.noctua.Model.Posts;
import com.nt.noctua.Model.Reactions;
import com.nt.noctua.Model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReactionsRepository extends JpaRepository<Reactions, Long> {

    Long countByPosts(Posts post);
    List<Reactions> findByPosts(Posts post);

    Reactions findByPostsAndUser(Posts post, ModelUser user);

}
