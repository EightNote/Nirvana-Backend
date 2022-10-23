package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.PlaylistRowMapper;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlaylistDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public PlaylistDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Track> getTracks(String playlistName) {
        String sql = "";
        // Get From PlaylistContent table
        return jdbcTemplate.query(sql, TrackRowMapper.trackRowMapper);
    }

    public Playlist getPlaylist(String playlistName) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, PlaylistRowMapper.playListRowMapper);
    }

    public List<String> getParticipants(String playlistName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, index) -> rs.getString("username"));
    }

    public void createPlaylist(String name, String desc, String type, String visibility, String created_by_user, String created_by_artist) {
        created_by_user = created_by_user == null ? "NULL" : created_by_user;
        created_by_artist = created_by_artist == null ? "NULL" : created_by_artist;

        String sql = "";
        jdbcTemplate.update(sql, name, desc, type, visibility, created_by_user, created_by_artist);
    }

    public void updatePlaylistDescription(String playlistName, String description) {
        String sql = "";
        jdbcTemplate.update(sql);
    }

    public void updatePlaylistVisibility(String playlistName, String visibility) {
        String sql = "";
        jdbcTemplate.update(sql);
    }

    public void deletePlaylist(String playlistName) {
        String sql = "";
        jdbcTemplate.update(sql);
    }

    public List<String> getLikes(String playlistName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, id) -> rs.getString("username"));
    }

    public void toggleLike(String username, String playlistName) {
        String sql = "";
        jdbcTemplate.update(sql, username, playlistName);
    }

    public String trackAddedBy(String playlistName, String trackName) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, (rs, id) -> rs.getString("added_by"));
    }

    public String getOwner(String playlistName) {
        String sql = "";
        Playlist p = jdbcTemplate.queryForObject(playlistName, PlaylistRowMapper.playListRowMapper);
        if (p.getCreatedByArtist() != null) return p.getCreatedByArtist();
        return p.getCreatedByUser();
    }
}
