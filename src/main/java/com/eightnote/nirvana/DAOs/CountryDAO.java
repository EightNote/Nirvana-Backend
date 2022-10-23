package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Country;
import com.eightnote.nirvana.row_mappers.CountryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CountryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addCountry(String countryName) throws DataAccessException {
        String sql = "INSERT INTO Country(name) VALUES (?);";
        jdbcTemplate.update(sql, countryName);
    }

    public List<Country> getAllCountries() {
        String sql = "SELECT * FROM Country;";
        return jdbcTemplate.query(sql, CountryRowMapper.countryRowMapper);
    }

}
