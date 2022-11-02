package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.RecordLabel;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.row_mappers.RecordLabelRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordLabelDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public RecordLabelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public RecordLabel getRecordLabel(Integer id) {
        String sql = "SELECT * FROM public.RecordLabel WHERE id= %d".formatted(id);
        return jdbcTemplate.queryForObject(sql, RecordLabelRowMapper.recordLabelRowMapper);
    }

    public List<RecordLabel> getAllRecordLabel(){
        String sql = "SELECT * FROM public.RecordLabel";
        return jdbcTemplate.query(
                sql,
                (rl, rowNum)->
                        new RecordLabel(
                                rl.getInt("id"),
                                rl.getString("username"),
                                rl.getString("email"),
                                rl.getString("password"),
                                rl.getBoolean("is_active"),
                                rl.getDate("created_at"),
                                rl.getDate("updated_at"),
                                rl.getString("labelName"),
                                rl.getString("description"),
                                rl.getURL("logo"),
                                rl.getURL("twitter"),
                                rl.getURL("facebook"),
                                rl.getURL("instagram")
                        )

        );
    }
}
