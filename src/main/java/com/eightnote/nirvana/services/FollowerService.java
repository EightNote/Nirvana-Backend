package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.FollowerDAO;
import com.eightnote.nirvana.models.NirvanaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowerService {
    @Autowired
    public final FollowerDAO followerDAO;

    public FollowerService(FollowerDAO followerDAO) {
        this.followerDAO = followerDAO;
    }

    public void addFollower(NirvanaUser artist, NirvanaUser user) {
        followerDAO.addFollower(artist, user);
    }

    public void removeFollower(NirvanaUser artist, NirvanaUser user) {
        followerDAO.removeFollower(artist, user);
    }

    public List<String> getFollowers(String username) {
        return followerDAO.getFollowersOf(username);
    }

    public boolean isFollowedBy(String artist, String user) {
        return followerDAO.isFollowedBy(artist, user);
    }
}
