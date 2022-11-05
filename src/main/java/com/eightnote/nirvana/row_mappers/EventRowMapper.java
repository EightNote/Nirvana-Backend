package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Event;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EventRowMapper {
    public static RowMapper<Event> eventRowMapper = (rs, rowNum) -> new Event(
            rs.getString("date"),
            rs.getString("time"),
            rs.getString("venue"),
            rs.getString("registration"),
            rs.getString("event_poster"),
            rs.getString("artist_id"),
            rs.getInt("country_id")
    );
}
