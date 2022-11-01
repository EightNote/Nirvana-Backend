package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.row_mappers.NirvanaUserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NirvanaUserDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public NirvanaUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(NirvanaUser user) {
        String sql = "INSERT INTO NirvanaUsers VALUES ('%s', '%s', true)".formatted( user.getUsername(), user.getPassword());

        String sql_role = "INSERT INTO Authorities VALUES ('%s', '%s')".formatted(user.getUsername(), user.getRole());
        jdbcTemplate.update(sql);
        jdbcTemplate.update(sql_role);
    }

    public NirvanaUser getUser(String username) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM (SELECT * FROM NirvanaUsers NATURAL JOIN Authorities) as user WHERE user.username = '%s'".formatted(username);
        return jdbcTemplate.queryForObject(sql, NirvanaUserRowMapper.nirvanaUserRowMapper);
    }

    public void removeUser(NirvanaUser user) {
        String sql = "DELETE FROM NirvanaUsers WHERE username = '%s'".formatted(user.getUsername());
        jdbcTemplate.update(sql);
    }
}
