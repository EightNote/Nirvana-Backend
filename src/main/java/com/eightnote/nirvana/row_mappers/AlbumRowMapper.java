package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Album;
import org.springframework.jdbc.core.RowMapper;

public class AlbumRowMapper {
    public static RowMapper<Album> albumRowMapper = (rs, rowNum) -> new Album(
            rs.getInt("id"),
            rs.getString("artist_id"),
            rs.getString("album_logo"),
            rs.getString("album_title"),
            rs.getInt("genre_id"));
}
