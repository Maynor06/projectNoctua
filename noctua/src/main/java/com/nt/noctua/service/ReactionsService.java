package com.nt.noctua.service;

import com.nt.noctua.Model.Posts;
import com.nt.noctua.Model.Reactions;
import com.nt.noctua.Model.ModelUser;
import com.nt.noctua.dto.ReactionsDTO;
import com.nt.noctua.repository.PostsRepository;
import com.nt.noctua.repository.ReactionsRepository;
import com.nt.noctua.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionsService {

    private final ReactionsRepository reactionsRepository;
    private final PostsRepository postsRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReactionsService(ReactionsRepository reactionsRepository, PostsRepository postsRepository, UserRepository userRepository) {
        this.reactionsRepository = reactionsRepository;
        this.postsRepository = postsRepository;
        this.userRepository = userRepository;
    }

    public ReactionsDTO reactionar(String postId, String userId){
        Posts post = postsRepository.findById(Long.parseLong(postId)).orElseThrow(() -> new IllegalArgumentException("el post no existe"));
        ModelUser user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new IllegalArgumentException("el usuario no existe"));

        Reactions reactions = new Reactions(post, user);
        reactions = reactionsRepository.save(reactions);

        return new ReactionsDTO(
                reactions.getId(),
                reactions.getPosts().getId(),
                reactions.getUser().getId(),
                reactions.getDateCreated()
        );
    }

    // obtener las reacciones de los posts
    public List<ReactionsDTO> getReactionsByPostId(String id){
        Posts post = postsRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("el post no existe"));
        List<Reactions> reactionsList =  reactionsRepository.findByPosts(post);

        return reactionsList.stream().map(reactions -> new ReactionsDTO(
                reactions.getId(),
                reactions.getPosts().getId(),
                reactions.getUser().getId(),
                reactions.getDateCreated()
        )).toList();
    }

    public Long getReactionsCountByPostId(String id){
        Posts post = postsRepository.findById(Long.parseLong(id)).orElseThrow(() -> new IllegalArgumentException("el post no existe"));
        return reactionsRepository.countByPosts(post);
    }

    public void deleteReaction(String postId, String userId){
        Posts post = postsRepository.findById(Long.parseLong(postId)).orElseThrow(() -> new IllegalArgumentException("el post no existe"));
        ModelUser user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> new IllegalArgumentException("el usuario no existe"));
        Reactions reactions = reactionsRepository.findByPostsAndUser(post, user);
        reactionsRepository.delete(reactions);

    }

}
