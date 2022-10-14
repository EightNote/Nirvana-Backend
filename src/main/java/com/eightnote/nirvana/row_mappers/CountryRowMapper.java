package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.Country;
import org.springframework.jdbc.core.RowMapper;

public class CountryRowMapper {
    public static RowMapper<Country> countryRowMapper = (rs, rowNum) -> new Country(rs.getInt("id"), rs.getString("name"));
}
