package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.RecordLabel;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RecordLabelRowMapper {
    public static RowMapper<RecordLabel> recordLabelRowMapper = (rl, rowNum) -> new RecordLabel(
            rl.getString("username"),
            rl.getString("description"),
            rl.getString("logo"),
            rl.getString("twitter"),
            rl.getString("facebook"),
            rl.getString("instagram")
    );
}
