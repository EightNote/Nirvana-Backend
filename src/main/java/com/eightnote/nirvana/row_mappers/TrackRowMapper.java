package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.PlaylistTrackInfo;
import com.eightnote.nirvana.models.Track;
import org.springframework.jdbc.core.RowMapper;

public class TrackRowMapper {

    public static RowMapper<Track> trackRowMapper=(rs, rowNum)->new Track(
            rs.getString("title"),
            rs.getString("audio_file"),
            rs.getInt("track_length"),
            rs.getBoolean("explicit_content"),
            rs.getString("writer"),
            rs.getString("composer"),
            rs.getString("producer"),
            rs.getString("lyrics"),
            rs.getInt("album_id")
    );

    public static RowMapper<PlaylistTrackInfo> playListTrackInfoRowMapper = (rs, rowNum) -> new PlaylistTrackInfo(
            rs.getString("title"),
            rs.getString("audio_file"),
            rs.getInt("track_length"),
            rs.getBoolean("explicit_content"),
            rs.getString("writer"),
            rs.getString("composer"),
            rs.getString("producer"),
            rs.getString("lyrics"),
            rs.getInt("album_id"),
            rs.getString("added_by_id")
    );
}
