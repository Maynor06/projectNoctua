package com.nt.noctua.service;

import com.nt.noctua.Model.Comment;
import com.nt.noctua.Model.Posts;
import com.nt.noctua.Model.ModelUser;
import com.nt.noctua.dto.CommentDTO;
import com.nt.noctua.repository.CommentRepository;
import com.nt.noctua.repository.PostsRepository;
import com.nt.noctua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostsRepository postsRepository;
    private UserRepository userRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, PostsRepository postsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
    }

    private CommentDTO convertCommentToCommentDTO(Comment comment) {
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getId(),
                comment.getPosts().getId(),
                comment.getContent(),
                comment.getDate_comment()
        );
    }

    //comentar
    public CommentDTO createComment(Long userId, Long postId, String content) {
        ModelUser user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("el usuario no existe :D post"));
        Posts posts = postsRepository.getById(postId);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPosts(posts);
        comment.setContent(content);

        comment = commentRepository.save(comment);
        return convertCommentToCommentDTO(comment);
    }

    public CommentDTO getCommentById(String id) {
        Comment comment = commentRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("el comentario no existe"));
        return convertCommentToCommentDTO(comment);
    }

    public List<CommentDTO> getCommentsByPostId(String postId) {
        Posts post = postsRepository.getById(Long.parseLong(postId));
        if(post == null){
            throw new IllegalArgumentException("el post no existe");
        }
        return commentRepository.findByPosts(post)
                .stream().map(this::convertCommentToCommentDTO).toList();
    }

    public void deleteComment(String id) {
        if(!commentRepository.existsById(Long.parseLong(id))){
            throw new IllegalArgumentException("el comentario no existe");
        }
        commentRepository.deleteById(Long.parseLong(id));
    }

    public CommentDTO editComment(CommentDTO commentDTO, String id) {
        Comment comment = commentRepository.findById(Long.parseLong(id) ).orElseThrow(() -> new IllegalArgumentException("el comentario no existe"));
        comment.setContent(commentDTO.getContent());

        comment = commentRepository.save(comment);
        return convertCommentToCommentDTO(comment);
    }



}
