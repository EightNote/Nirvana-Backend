package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Country;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
import com.eightnote.nirvana.row_mappers.CountryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

    public void createAlbum(String albumName, String albumLogo, String artistId, int genreId) {
        String sql = "";
        jdbcTemplate.update(sql, albumName, albumLogo, artistId, genreId);
    }

    public List<Country> getReleaseCountries(String album) {
        String sql = "";
        return jdbcTemplate.query(sql, CountryRowMapper.countryRowMapper);
    }

    public void like(String album) {
        String sql = ""; // Use arithmetic
        jdbcTemplate.update(sql);
    }

    public int getLikeCount(String albumName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> null).size();
    }

    public List<String> getLikes(String albumName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }

    public boolean isLikedBy(String albumName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> null).size() == 1;
    }

    public boolean isReleasedInCountry(String albumName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> null).size() == 1;
    }
}
