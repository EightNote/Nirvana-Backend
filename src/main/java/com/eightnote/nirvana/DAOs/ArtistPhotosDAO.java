package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.ArtistPhotos;
import com.eightnote.nirvana.row_mappers.ArtistPhotosRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.SimpleTimeZone;

@Component
public class ArtistPhotosDAO {
    @Autowired
    private final  JdbcTemplate jdbcTemplate;

    public ArtistPhotosDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ArtistPhotos> getImages(String artistName) {
        String sql = "";
        return jdbcTemplate.query(sql, ArtistPhotosRowMapper.imageLinksRowMapper);
    }

}
