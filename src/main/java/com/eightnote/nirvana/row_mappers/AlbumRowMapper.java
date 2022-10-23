package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Album;
import org.springframework.jdbc.core.RowMapper;

public class AlbumRowMapper {
    public static RowMapper<Album> albumRowMapper = (rs, rowNum) -> new Album(
            rs.getInt("id"),
            rs.getInt("artist_id"),
            rs.getInt("likes"),
            rs.getString("album_logo"),
            rs.getString("album_title"),
            rs.getString("genre"));
}
