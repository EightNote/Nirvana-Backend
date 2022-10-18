package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.row_mappers.PlaylistRowMapper;
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

    public List<Integer> getTrackIDs(String playlistName) {
        String sql = "";
        // Get From PlaylistContent table
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("trackID"));
    }

    public Playlist getPlaylist(String playlistName) {
        String sql = "";
        return jdbcTemplate.queryForObject(sql, PlaylistRowMapper.playListRowMapper);
    }

    public List<String> getUserNames(String playlistName) {
        String sql = "";
        return jdbcTemplate.query(sql, (rs, index) -> rs.getString("username"));
    }
}
