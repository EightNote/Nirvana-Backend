package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.ArtistDetails;
import org.springframework.jdbc.core.RowMapper;

public class ArtistDetailsRowMapper {
    public static RowMapper<ArtistDetails> artistDetailsRowMapper = (rs, rowNum) -> new ArtistDetails(
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("about"),
            rs.getString("twitter"),
            rs.getString("facebook"),
            rs.getString("instagram"),
            rs.getInt("record_label_id"),
            rs.getInt("nationality_id")
    );
}
