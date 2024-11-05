package com.nt.noctua.repository;

import com.nt.noctua.Model.Comment;
import com.nt.noctua.Model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByPosts(Posts postId);

}
