package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Event;
import com.eightnote.nirvana.row_mappers.EventRowMapper;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EventDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(String date, String time, String venue, String registrationLink, String posterUrl, String artistName, int country) {
        String sql = "INSERT INTO Event (date,time,venue,registration,event_poster,artist_id,country_id) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', %d)"
                .formatted(date, time, venue, registrationLink, posterUrl, artistName, country);
        System.out.println(sql);
        jdbcTemplate.update(sql);
    }

    public Event getEvent(String id) {
        String sql = "SELECT * FROM Event WHERE id = %d;".formatted(id);
        return jdbcTemplate.queryForObject(sql, EventRowMapper.eventRowMapper);
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM Event;";
        return jdbcTemplate.query(sql, EventRowMapper.eventRowMapper);
    }

    public void updateEventTime(String reg, String time) {
        String sql = "UPDATE Event SET time = '%s' WHERE registration = '%s';".formatted(time,reg);

        jdbcTemplate.update(sql);
    }

    public void updateEventDate(String reg, String date) {
        String sql = "UPDATE Event SET date = '%s' WHERE registration = '%s';".formatted(date,reg);

        jdbcTemplate.update(sql);
    }

    public void updateEventVenue(String reg, String venue) {
        String sql = "UPDATE Event SET venue = '%s' WHERE registration = '%s';".formatted(venue,reg);

        jdbcTemplate.update(sql);
    }
}
