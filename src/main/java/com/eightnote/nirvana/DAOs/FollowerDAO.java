package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.row_mappers.FollowerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowerDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FollowerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDetails> getFollowersOf(String username) {
        String sql = "SELECT FROM Followers WHERE artist='%s'".formatted(username);
        return jdbcTemplate.query(sql, FollowerRowMapper.followerRowMapper);
    }

    public boolean isFollowedBy(String artist, String user) {
        String sql = "SELECT FROM Followers WHERE artist='%s' AND followed_by='%s".formatted(artist, user);
        return jdbcTemplate.query(sql, FollowerRowMapper.followerRowMapper).isEmpty();
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
