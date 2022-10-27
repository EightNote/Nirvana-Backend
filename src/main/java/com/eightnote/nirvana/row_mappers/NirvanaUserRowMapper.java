package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.NirvanaUser;
import org.springframework.jdbc.core.RowMapper;

public class NirvanaUserRowMapper {

    public static RowMapper<NirvanaUser> nirvanaUserRowMapper = (rs, rowNum) -> new NirvanaUser(
            rs.getString("username"),
            rs.getString("password")
    );
}
