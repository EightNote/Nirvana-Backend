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
        String sql =
                ("SELECT title, audio_file, track_length, explicit_content, writer, composer, producer, lyrics, album_id " +
                "FROM Track WHERE album_id " +
                        "IN " +
                            "(SELECT Album.id FROM Album " +
                                "WHERE Album.genre_id IN (SELECT Genre.id FROM Genre WHERE Genre.name = '%s')" +
                            ");").formatted(genre);
        return jdbcTemplate.query(sql, TrackRowMapper.trackRowMapper);
    }

    public void createGenre(String genreName) {
        String sql = "INSERT INTO Genre(name) VALUES (?)";
        jdbcTemplate.update(sql, genreName);
    }

    public Genre getGenre(String genreName) {
        String sql = "SELECT * FROM Genre WHERE name = %s".formatted(genreName);
        return jdbcTemplate.queryForObject(sql, GenreRowMapper.genreRowMapper);
    }

    public List<Genre> allGenres() {
        String sql = "SELECT * FROM Genre";
        return jdbcTemplate.query(sql,(rs, rowNum)->
                new Genre(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
    }
}
