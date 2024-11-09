package com.nt.noctua.Controller;

import com.nt.noctua.dto.PostCreateDTO;
import com.nt.noctua.dto.PostDTO;
import com.nt.noctua.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/createPost")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateDTO postCreateDTO){
        PostDTO post = postService.createPost(postCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/postById/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String id){
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("/getPosts")
    public ResponseEntity<List<PostDTO>> getPosts(){
        List<PostDTO> posts = postService.getPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable String userId){
        List<PostDTO> listPost = postService.getPostsByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(listPost);
    }

    @PutMapping("/editPost/{id}")
    public ResponseEntity<PostDTO> editPost(@RequestBody PostDTO postDTO,@PathVariable String id){
        PostDTO post = postService.editPost(postDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id){
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }


}
