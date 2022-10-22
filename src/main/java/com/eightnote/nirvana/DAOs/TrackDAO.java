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
        String sql="";
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

    public String getArtist(String track) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> rs.getString("artist_name"));
    }

    public List<String> getLikes(String track) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }

    public boolean isLikedBy(String track, String username) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> null).size() == 1;
    }
}
