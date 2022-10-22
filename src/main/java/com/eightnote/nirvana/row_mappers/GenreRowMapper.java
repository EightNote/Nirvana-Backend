package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Genre;
import com.eightnote.nirvana.models.Playlist;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreRowMapper {
    public static RowMapper<Genre> genreRowMapper = (rs, rowNum) -> new Genre(
            rs.getInt("id"),
            rs.getString("name")
    );
}
