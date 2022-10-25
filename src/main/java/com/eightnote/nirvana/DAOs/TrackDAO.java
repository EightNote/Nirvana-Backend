package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public TrackDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Track getTrack(String trackName) {
        String sql="SELECT * FROM Track WHERE title = %s".formatted(trackName);
        return jdbcTemplate.queryForObject(sql, TrackRowMapper.trackRowMapper);
    }

    public void createTrack(String trackName){
        String sql="";
        jdbcTemplate.update(sql,trackName);
    }

    public Album getAlbum(String track) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, AlbumRowMapper.albumRowMapper);
    }

    public List<String> getLikes(String track) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }

    public void toggleLike(String likedByUsername, String trackName, boolean unlike) {
        String sql = unlike ? ("DELETE FROM TrackLikes " +
                "WHERE " +
                "track_id IN (SELECT id FROM Track WHERE title = %s)) " +
                "AND liked_by_id = %s").formatted(trackName, likedByUsername) :

                "INSERT INTO TrackLikes(((SELECT id FROM Track WHERE title = %s)), like_by_id) " +
                        "VALUES (%s, %s);".formatted(trackName, likedByUsername);
        jdbcTemplate.update(sql);
    }
}
