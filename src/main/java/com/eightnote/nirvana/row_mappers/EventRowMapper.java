package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Event;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EventRowMapper {
    public static RowMapper<Event> eventRowMapper = (rs, rowNum) -> new Event(
            rs.getInt("id"),
            rs.getDate("date"),
            rs.getTime("time"),
            rs.getString("venue"),
            rs.getString("registration"),
            rs.getString("eventPoster"),
            rs.getInt("artistID"),
            rs.getInt("countryID")
    );
}
