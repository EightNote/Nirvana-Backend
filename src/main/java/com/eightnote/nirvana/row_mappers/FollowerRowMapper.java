package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Country;
import org.springframework.jdbc.core.RowMapper;

public class FollowerRowMapper {
    public static RowMapper<Country> countryRowMapper = (rs, rowNum) -> null;
}
