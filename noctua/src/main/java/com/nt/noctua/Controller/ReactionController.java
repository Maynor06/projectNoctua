package com.nt.noctua.Controller;

import com.nt.noctua.dto.ReactionsDTO;
import com.nt.noctua.service.ReactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionsService reactionsService;

    @Autowired
    public ReactionController(ReactionsService reactionsService) {
        this.reactionsService = reactionsService;
    }

    @PostMapping("/react")
    public ResponseEntity<ReactionsDTO> react(@RequestParam String postId, @RequestParam String userId){
        ReactionsDTO reactionsDTO = reactionsService.reactionar(postId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(reactionsDTO);
    }

    @GetMapping("/getReactions")
    public ResponseEntity<List<ReactionsDTO>> getReactions(@RequestParam String postId){
        List<ReactionsDTO> reactionsDTOList = reactionsService.getReactionsByPostId(postId);
        return ResponseEntity.ok(reactionsDTOList);
    }

    @GetMapping("/getNumberReactions")
    public ResponseEntity<Long> getNumberReactions(@RequestParam String postId){
        Long numberReactions = reactionsService.getReactionsCountByPostId(postId);
        return ResponseEntity.ok(numberReactions);
    }

    @DeleteMapping("/unreact")
    public ResponseEntity<ReactionsDTO> unreact(@RequestParam String postId, @RequestParam String userId){
        reactionsService.deleteReaction(postId, userId);
        return ResponseEntity.ok().build();
    }

}
