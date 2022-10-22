package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Genre;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.GenreRowMapper;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public GenreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Track> getTracks(String genre) {
        String sql = "";
        return jdbcTemplate.query(sql, TrackRowMapper.trackRowMapper);
    }

    public void createGenre(String genreName) {
        String sql = "";
        jdbcTemplate.update(sql, genreName);
    }

    public Genre getGenre(String genreName) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, GenreRowMapper.genreRowMapper);
    }
}
