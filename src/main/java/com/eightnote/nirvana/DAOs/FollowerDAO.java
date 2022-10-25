package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.row_mappers.FollowerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowerDAO {
    public final String follower_table = "Followers";
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public FollowerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<UserDetails> getFollowersOf(String username) {
        String sql = "SELECT FROM %s WHERE artist='%s'".formatted(follower_table, username);
        return jdbcTemplate.query(sql, FollowerRowMapper.followerRowMapper);
    }

    public boolean isFollowedBy(String artist, String user) {
        String sql = "SELECT FROM %s WHERE artist='%s' AND followed_by='%s;".formatted(follower_table, artist, user);
        return jdbcTemplate.query(sql, FollowerRowMapper.followerRowMapper).isEmpty();
    }

    public void addFollower(UserDetails user1, UserDetails user2) {
        String artist = user1.getUsername();
        String user = user2.getUsername();

        String sql = "INSERT INTO %s VALUES ('%s', '%s');".formatted(follower_table, artist, user);
        jdbcTemplate.update(sql);
    }

    public void removeFollower(UserDetails u1, UserDetails u2) {
        String artist = u1.getUsername();
        String user = u2.getUsername();

        String sql = "DELETE FROM %s WHERE artist='%s' AND followed_by='%s'".formatted(follower_table, artist, user);
        jdbcTemplate.update(sql, artist, user);
    }
}
