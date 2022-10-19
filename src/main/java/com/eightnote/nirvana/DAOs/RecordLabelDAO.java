package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.RecordLabel;
import com.eightnote.nirvana.row_mappers.RecordLabelRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RecordLabelDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RecordLabelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RecordLabel getRecordLabel(Integer id) {
        String sql = "SELECT * FROM public.RecordLabel WHERE id=" + id;
        return jdbcTemplate.queryForObject(sql, RecordLabelRowMapper.recordLabelRowMapper);
    }
}
