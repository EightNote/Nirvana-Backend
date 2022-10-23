package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Event;
import com.eightnote.nirvana.row_mappers.EventRowMapper;
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

    public void create(String date, String time, String venue, String registrationLink, String posterUrl, String artistName, String country) {
        String sql = "";
        jdbcTemplate.update(sql, date, time, venue, registrationLink, posterUrl, artistName, country);
    }

    public Event getEvent(int id) {
        String sql = "SELECT * FROM Event WHERE id = %d;".formatted(id);
        return jdbcTemplate.queryForObject(sql, EventRowMapper.eventRowMapper);
    }

    public List<Event> getAllEvents() {
        String sql = "SELECT * FROM Event;";
        return jdbcTemplate.query(sql, EventRowMapper.eventRowMapper);
    }

    public void updateEventTime(int id, String time) {
        String sql = "";
        jdbcTemplate.update(sql, id, time);
    }

    public void updateEventDate(int id, String date) {
        String sql = "";
        jdbcTemplate.update(sql, id, date);
    }

    public void updateEventVenue(int id, String venue) {
        String sql = "";
        jdbcTemplate.update(sql, id, venue);
    }
}
