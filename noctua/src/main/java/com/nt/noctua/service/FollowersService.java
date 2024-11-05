package com.nt.noctua.service;

import com.nt.noctua.Model.FollowerId;
import com.nt.noctua.Model.Followers;
import com.nt.noctua.repository.FollowersRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowersService {

    private FollowersRepository followersRepository;

    @Autowired
    public FollowersService(FollowersRepository followersRepository) {
        this.followersRepository = followersRepository;
    }

    @Transactional
    public void followUser(String userId, String followerId){

        if(!followersRepository.existsById_UserIdAndId_FollowerId(Integer.parseInt(userId) ,Integer.parseInt(followerId)) ){
            followersRepository.save(new Followers(Integer.parseInt(userId) ,Integer.parseInt(followerId)));
        }

    }

    @Transactional
    public void unfollowUser(String userId, String followerId){
        FollowerId followerId1 = new FollowerId(Integer.parseInt(userId), Integer.parseInt(followerId));
        followersRepository.deleteById(followerId1);
    }

    public List<Integer> getFollowers(String userId){
        return followersRepository.findFollowersByUserId(Integer.parseInt(userId));
    }
}
