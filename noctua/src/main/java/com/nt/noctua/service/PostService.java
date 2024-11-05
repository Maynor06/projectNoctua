package com.nt.noctua.service;

import com.nt.noctua.Model.Posts;
import com.nt.noctua.Model.ModelUser;
import com.nt.noctua.dto.PostCreateDTO;
import com.nt.noctua.dto.PostDTO;
import com.nt.noctua.repository.PostsRepository;
import com.nt.noctua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostsRepository postRepository;

    private UserRepository userRepository;

    @Autowired
    public PostService(PostsRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public PostDTO createPost(PostCreateDTO postCreateDTO) {
        ModelUser user = userRepository.findById(postCreateDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("el usuario no existe :D post"));

        Posts post = new Posts();
        post.setUser(user);
        post.setContent(postCreateDTO.getContent());

        post = postRepository.save(post);
        return new PostDTO(post);
    }

    public PostDTO editPost(PostDTO postDTO, String id) {
        Posts post = postRepository.findById(Long.parseLong(id) ).orElseThrow(() -> new IllegalArgumentException("post no encontrado"));
        post.setContent(postDTO.getContent());
        post = postRepository.save(post);
        return new PostDTO(post);
    }

    public List<PostDTO> getPostsByUserId(String userId) {
        return postRepository.findByUserId(Long.parseLong(userId)).
                stream().map(PostDTO::new).
                collect(Collectors.toList());

    }

    public PostDTO getPostById(String id) {
        Posts post = postRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("post no encontrado"));
        return new PostDTO(post);
    }
    public void deletePost(String id) {
        if(!postRepository.existsById(Long.parseLong(id))){
            throw new IllegalArgumentException("post no encontrado");
        }
        postRepository.deleteById(Long.parseLong(id));
    }
}
