package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.RecordLabel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RecordLabelRowMapper {
    public static RowMapper<RecordLabel> recordLabelRowMapper = (rl, rowNum) -> new RecordLabel(
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
    );
}
