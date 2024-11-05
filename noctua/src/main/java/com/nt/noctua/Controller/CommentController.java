package com.nt.noctua.Controller;

import com.nt.noctua.dto.CommentDTO;
import com.nt.noctua.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public ResponseEntity<CommentDTO> addComent(@RequestBody CommentDTO commentDTO){
        commentService.createComment(commentDTO.getUserId(), commentDTO.getPostId(), commentDTO.getContent());

        return ResponseEntity.status(HttpStatus.CREATED).body(commentDTO);
    }

    @GetMapping("/getComment/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@RequestParam String id){
        CommentDTO commentDTO = commentService.getCommentById(id);

        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

    @GetMapping("/getComments/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable String id ){
        List<CommentDTO> listComments = commentService.getCommentsByPostId(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(listComments);
    }

    @PutMapping("/editComment/{id}")
    public ResponseEntity<CommentDTO> editComment(@RequestBody CommentDTO commentDTO, @PathVariable String id){
        commentService.editComment(commentDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

    @DeleteMapping("/deleteComment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id){
        commentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
