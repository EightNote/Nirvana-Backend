package com.eightnote.nirvana.DAOs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class FollowerDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FollowerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDetails> getFollowersOf(String username) {
        return new ArrayList<>();
    }

    public void addFollower(UserDetails user1, UserDetails user2) {
        String username1 = user1.getUsername();
        String username2 = user2.getUsername();

        String sql = "";
        jdbcTemplate.update(sql, username1, username2);
    }

    public void removeFollower(UserDetails u1, UserDetails u2) {
        String username1 = u1.getUsername();
        String username2 = u2.getUsername();

        String sql = "";
        jdbcTemplate.update(sql, username1, username2);
    }
}
