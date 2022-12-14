package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Country;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
import com.eightnote.nirvana.row_mappers.CountryRowMapper;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AlbumDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Album getAlbum(String albumName) {
        String sql =
                ("SELECT Album.id, album_title, album_logo, artist_id, name as genre, COUNT(liked_by_id) as likes " +
                "FROM Album, Genre, AlbumLikes " +
                "WHERE genre_id = Genre.id AND Album.id = album_id " +
                "GROUP BY album_id HAVING album_title = '%s';")
                .formatted(albumName);
        return jdbcTemplate.queryForObject(sql, AlbumRowMapper.albumRowMapper);
    }

    public Integer createAlbum(String albumName, String albumLogo, String artistId, int genreId) {
        String sql = "INSERT INTO Album(album_title, album_logo, artist_id, genre_id) VALUES('%s', '%s', '%s', %d);"
                .formatted(albumName, albumLogo, artistId, genreId);
        String sql2 = "SELECT MAX(id) FROM Album";
        jdbcTemplate.update(sql);
        return jdbcTemplate.queryForObject(sql2, Integer.class);
    }

    public List<Country> getReleaseCountries(String album) {
        String sql =
                "SELECT Country.id, Country.name FROM Album, AlbumReleaseInfo, Country " +
                ("WHERE " +
                        "Album.id = AlbumReleaseInfo.album_id " +
                        "AND AlbumReleaseInfo.country_id = Country.id " +
                        "AND album_title = %s")
                        .formatted(album);
        return jdbcTemplate.query(sql, CountryRowMapper.countryRowMapper);
    }

    public void toggleLike(String username, Integer id, boolean unlike) {
        String sql = unlike ? ("DELETE FROM AlbumLikes " +
                "WHERE album_id = %s AND liked_by_id = '%s'").formatted(id, username) :
                "INSERT INTO AlbumLikes(album_id, liked_by_id) " +
                        "VALUES ( %s, '%s');".formatted(id, username);
        jdbcTemplate.update(sql);
    }

    public List<String> getLikes(String albumName) {
        String sql =
                ("SELECT liked_by_id FROM AlbumLikes " +
                        "WHERE Album.id IN (SELECT id FROM ALBUM WHERE album_title = %s);")
                        .formatted(albumName);
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }

    public List<Album> getAlbumsByArtist(String artist) {
        String sql = "SELECT * FROM Album WHERE artist_id='%s';".formatted(artist);
        return jdbcTemplate.query(sql, AlbumRowMapper.albumRowMapper);
    }

    public List<Album> getAllAlbums() {
        String sql = "SELECT * FROM Album;";
        return jdbcTemplate.query(sql, AlbumRowMapper.albumRowMapper);
    }

    public List<Album> getUserLikes(String username) {
        String sql = "SELECT * FROM AlbumLikes WHERE liked_by_id = %s".formatted(username);
        List<Album> likes;

        System.out.printf("Got username %s%n", username);
        try {
            likes = jdbcTemplate.query(sql, AlbumRowMapper.albumRowMapper);
        } catch (DataAccessException e) {
            return new ArrayList<>();
        };

        return likes;
    }

    public List<Track> getAlbumTracks(Integer id) {
        String sql = "SELECT * FROM Track WHERE album_id = %s".formatted(id);
        return jdbcTemplate.query(sql, TrackRowMapper.trackRowMapper);
    }

    public List<Album> likedAlbums(String username) {
        String sql= "SELECT * FROM Album WHERE Album.id IN (SELECT album_id FROM AlbumLikes WHERE liked_by_id LIKE '%s')".formatted(username);
        return jdbcTemplate.query(sql, AlbumRowMapper.albumRowMapper);
    }

    public Album getAlbumById(Integer id) {
        String sql = "SELECT * FROM Album WHERE id=%s".formatted(id);
        return jdbcTemplate.queryForObject(sql, AlbumRowMapper.albumRowMapper);
    }

    public Integer isLiked(String username, Integer id) {
        String sql = "SELECT COUNT(*) FROM AlbumLikes WHERE album_id=%s AND liked_by_id='%s'".formatted(id, username);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
