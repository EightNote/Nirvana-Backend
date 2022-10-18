package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Playlist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaylistRowMapper {
    public static RowMapper<Playlist> playListRowMapper = (rs, rowNum) -> new Playlist(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("type"),
            rs.getBoolean("visibility"),
            rs.getString("createdByUser"),
            rs.getString("createdByArtist")
    );
}
