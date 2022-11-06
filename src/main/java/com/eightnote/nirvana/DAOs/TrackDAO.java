package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
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

    public List<Track> getTrack(String trackName) {
        String sql="SELECT * FROM Track WHERE title LIKE '%s%s'".formatted(trackName,"%");
        System.out.println(sql);
        return jdbcTemplate.query(sql, (rs, rowNum)->
                new Track(
                        rs.getString("title"),
                        rs.getString("audio_file"),
                        rs.getInt("track_length"),
                        rs.getBoolean("explicit_content"),
                        rs.getString("writer"),
                        rs.getString("composer"),
                        rs.getString("producer"),
                        rs.getString("lyrics"),
                        rs.getInt("album_id")
                ));
    }

    public  List<Track> getAllTrack() {
        String sql="SELECT * FROM Track";
        return jdbcTemplate.query(sql,
                (rs, rowNum)->
                        new Track(
                                rs.getString("title"),
                                rs.getString("audio_file"),
                                rs.getInt("track_length"),
                                rs.getBoolean("explicit_content"),
                                rs.getString("writer"),
                                rs.getString("composer"),
                                rs.getString("producer"),
                                rs.getString("lyrics"),
                                rs.getInt("album_id")
                        )
                );
    }


    public void createTrack(Track track){
        String sql=("INSERT INTO Track(title, audio_file, track_length, explicit_content, writer, composer, producer, lyrics, album_id) " +
                "VALUES ('%s', '%s', '%d', '%d', '%s', '%s', '%s', '%s', '%d');").formatted(
                track.getTitle(), track.getAudio_file(),
                track.getTrack_length(), track.getExplicit_content() ? 1 : 0,
                track.getWriter(), track.getComposer(), track.getProducer(),
                track.getLyrics(), track.getAlbum_id());
        jdbcTemplate.update(sql);
    }

    public Album getAlbum(String trackName) {
        String sql = "SELECT * FROM Track ";
        return jdbcTemplate.queryForObject(sql, AlbumRowMapper.albumRowMapper);
    }

    public List<String> getLikes(String track) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("username"));
    }

    public void toggleLike(String likedByUsername, String trackName, boolean unlike) {
        String sql = unlike ? ("DELETE FROM TrackLikes " +
                "WHERE " +
                "track_id IN (SELECT id FROM Track WHERE title = '%s')) " +
                "AND liked_by_id = %s").formatted(trackName, likedByUsername) :

                "INSERT INTO TrackLikes(((SELECT id FROM Track WHERE title = '%s')), like_by_id) " +
                        "VALUES (%s, %s);".formatted(trackName, likedByUsername);
        jdbcTemplate.update(sql);
    }
}
