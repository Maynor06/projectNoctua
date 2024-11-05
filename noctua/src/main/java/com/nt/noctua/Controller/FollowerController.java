package com.nt.noctua.Controller;

import com.nt.noctua.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followers")
public class FollowerController {

    private final FollowersService followersService;

    @Autowired
    public FollowerController(FollowersService followersService) {
        this.followersService = followersService;
    }

    @PostMapping("/follow")
    public ResponseEntity<String> followUser(@RequestParam String userId, @RequestParam String followerId){
        followersService.followUser(userId, followerId);
        return ResponseEntity.ok("User followed successfully");
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam String userId, @RequestParam String followerId){
        followersService.unfollowUser(userId, followerId);
        return ResponseEntity.ok("User unfollowed successfully");
    }

    @GetMapping("/getFollowers")
    public ResponseEntity<List<Integer>> getFollowers(@RequestParam String userId){
        List<Integer> list = followersService.getFollowers(userId);
        return ResponseEntity.ok(list);
    }

}
