package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenreDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public GenreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    public Genre getGenre(String genreName) {
//        String sql = "";
//        return jdbcTemplate.query()
//    }
}
