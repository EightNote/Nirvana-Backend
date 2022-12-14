package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Playlist;
import com.eightnote.nirvana.models.PlaylistTrackInfo;
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

    public List<PlaylistTrackInfo> getTracks(int playlistID) {
        String sql = ("SELECT * FROM Track WHERE Track.title IN  (SELECT track_id FROM PlaylistContent WHERE playlist_id = %d);").formatted(playlistID);
        return jdbcTemplate.query(sql, TrackRowMapper.playListTrackInfoRowMapper);
    }

    public Playlist getPlaylist(int playlistID) {
        String sql = "SELECT * FROM Playlist WHERE id = %d".formatted(playlistID);
        return jdbcTemplate.queryForObject(sql, PlaylistRowMapper.playListRowMapper);
    }

    public List<String> getParticipants(int playlistID) {
        String sql = "SELECT participant_id FROM PlaylistParticipation WHERE playlist_id = %d;".formatted(playlistID);
        return jdbcTemplate.query(sql, (rs, index) -> rs.getString("participant_id"));
    }

    public void createPlaylist(String name, String desc, String type, String visibility, String created_by_user, String created_by_artist) {
        created_by_user = created_by_user == null ? "NULL" : "'" + created_by_user + "'";
        created_by_artist = created_by_artist == null ? "NULL" : "'" + created_by_artist + "'";

        String sql = "INSERT INTO Playlist(name, description, type, visibility, created_by_artist_id, created_by_user_id)  " +
                "VALUES ('%s', '%s', '%s', '%s', %s, %s);".formatted(name, desc, type, visibility,  created_by_artist, created_by_user);
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    public void updatePlaylistDescription(int playlistID, String description) {
        String sql = "UPDATE Playlist SET description = %s WHERE id = %d".formatted(description, playlistID);
        jdbcTemplate.update(sql);
    }

    public void updatePlaylistVisibility(int playlistID, String visibility) {
        String sql = "UPDATE Playlist SET visibility = %s WHERE id = %d".formatted(visibility, playlistID);
        jdbcTemplate.update(sql);
    }

    public void deletePlaylist(int playlistID) {
        String sql = "DELETE FROM Playlist WHERE id = %d".formatted(playlistID);
        jdbcTemplate.update(sql);
    }

    public List<String> getLikes(int playlistID) {
        String sql = "SELECT liked_by_id as username FROM PlaylistLikes WHERE playlist_id = %d;".formatted(playlistID);
        return jdbcTemplate.query(sql, (rs, id) -> rs.getString("username"));
    }

    public void toggleLike(String username, int playlistID, boolean unlike) {
        String sql = unlike ? ("DELETE FROM PlaylistLikes " +
                "WHERE " +
                "playlist_id = %d " +
                "AND liked_by_id = '%s'").formatted(playlistID, username) :

                "INSERT INTO PlaylistLikes(playlist_id, like_by_id) " +
                        "VALUES (%d, '%s');".formatted(playlistID, username);
        jdbcTemplate.update(sql);
    }

    public int getPlaylistID(String username, String playlistName) {
        String sql = ("SELECT id FROM Playlist " +
                "WHERE (created_by_artist_id = '%s' OR created_by_user_id = '%s') AND name = '%s';")
                .formatted(username, username, playlistName);
        System.out.println(sql);
        Integer id = jdbcTemplate.queryForObject(sql, (rs, i) -> rs.getInt("id"));
        if (id == null) return -1;
        return id;
    }

    public List<Playlist> getPlaylistByUser(String username) {
        String sql = "SELECT * FROM Playlist WHERE (created_by_artist_id = '%s' OR created_by_user_id = '%s')".formatted(username, username);
        return jdbcTemplate.query(sql, PlaylistRowMapper.playListRowMapper);
    }

    public List<Playlist> getAllPlaylists() {
        String sql = "SELECT * FROM Playlist";
        return jdbcTemplate.query(sql, PlaylistRowMapper.playListRowMapper);
    }

    public void addTrack(String trackName, int playlistID, String addedBy) {
        String sql = "INSERT INTO PlaylistContent(added_by_id, playlist_id, track_id) VALUES('%s', %d, '%s');".formatted(addedBy, playlistID, trackName);
        jdbcTemplate.update(sql);
    }

    public void addParticipant(int playlistID, String username) {
        String sql = "INSERT INTO PlaylistParticipation(playlist_id, participant_id) VALUES (%d, '%s');".formatted(playlistID, username);
        jdbcTemplate.update(sql);
    }
}
