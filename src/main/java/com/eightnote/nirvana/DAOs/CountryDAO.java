package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Country;
import com.eightnote.nirvana.row_mappers.CountryRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CountryDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CountryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void addCountry(String countryName) throws DataAccessException {
        String sql = "";
        try {
            jdbcTemplate.update(sql, countryName);
        }
        catch (DataAccessException d) {
           throw d;
        }
    }

    public boolean deleteCountry(int id) {
        String sql = "";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public List<Country> getAllCountries() {
        String sql = "";
        return jdbcTemplate.query(sql, CountryRowMapper.countryRowMapper);
    }

}
