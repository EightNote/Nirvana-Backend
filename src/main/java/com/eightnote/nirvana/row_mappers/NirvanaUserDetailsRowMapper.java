package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.ArtistDetails;
import com.eightnote.nirvana.models.UserDetails;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;

public class NirvanaUserDetailsRowMapper {
    public static RowMapper<ArtistDetails> artistDetailsRowMapper = (rs, rowNum) -> new ArtistDetails(
            rs.getString("username"),
            rs.getString("about"),
            rs.getString("twitter"),
            rs.getString("facebook"),
            rs.getString("instagram"),
            rs.getString("record_label_id"),
            rs.getInt("nationality_id")
    );

    public static RowMapper<UserDetails> userDetailsRowMapper = (rs, rowNum) -> new UserDetails(
            rs.getString("username"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("date_of_birth"),
            rs.getString("gender"),
            new ArrayList<>()
    );
}
