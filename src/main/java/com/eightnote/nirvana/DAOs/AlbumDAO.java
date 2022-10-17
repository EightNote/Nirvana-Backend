package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AlbumDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AlbumDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Album getAlbum(String albumName) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, AlbumRowMapper.albumRowMapper);
    }

    public void createAlbum(String albumName) {
        String sql = "";
        jdbcTemplate.update(sql, albumName);
    }
}
