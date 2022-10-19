package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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
}
