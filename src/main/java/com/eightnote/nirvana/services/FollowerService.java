package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.FollowerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class FollowerService {
    @Autowired
    public final FollowerDAO followerDAO;

    public FollowerService(FollowerDAO followerDAO) {
        this.followerDAO = followerDAO;
    }

    public void addFollower(UserDetails u1, UserDetails u2) {
        followerDAO.addFollower(u1, u2);
    }

    public void removeFollower(UserDetails u1, UserDetails u2) {
        followerDAO.removeFollower(u1, u2);
    }
}
