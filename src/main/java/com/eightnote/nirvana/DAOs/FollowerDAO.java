package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.services.NirvanaUserService;
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

    @Autowired
    private final NirvanaUserService nirvanaUserService;

    public FollowerDAO(JdbcTemplate jdbcTemplate, NirvanaUserService nirvanaUserService) {
        this.jdbcTemplate = jdbcTemplate;
        this.nirvanaUserService = nirvanaUserService;
    }

    public List<String> getFollowersOf(String username) {
        String sql = "SELECT * FROM %s WHERE artist_id = '%s';".formatted(follower_table, username);
        return jdbcTemplate.query(sql, (rs, rowNum) -> (rs.getString("followed_by_id")));
    }

    public boolean isFollowedBy(String artist, String user) {
        String sql = "SELECT * FROM %s WHERE artist_id ='%s' AND followed_by_id = '%s;".formatted(follower_table, artist, user);
        return jdbcTemplate.query(sql, (rs, rowNum) -> nirvanaUserService.loadUserByUsername(rs.getString("followed_by_id"))).isEmpty();
    }

    public void addFollower(NirvanaUser _artist, NirvanaUser _user) {
        String artist = _artist.getUsername();
        String user = _user.getUsername();

        String sql = "INSERT INTO %s(artist_id, followed_by_id) VALUES ('%s', '%s');".formatted(follower_table, artist, user);
        jdbcTemplate.update(sql);
    }

    public void removeFollower(UserDetails u1, UserDetails u2) {
        String artist = u1.getUsername();
        String user = u2.getUsername();

        String sql = "DELETE FROM %s WHERE artist_id = '%s' AND followed_by_id = '%s'".formatted(follower_table, artist, user);
        jdbcTemplate.update(sql);
    }
}
