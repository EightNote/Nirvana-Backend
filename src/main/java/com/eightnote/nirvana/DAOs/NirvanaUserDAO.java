package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.ArtistAccountDetails;
import com.eightnote.nirvana.models.ArtistDetails;
import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.models.UserAccountDetails;
import com.eightnote.nirvana.row_mappers.NirvanaUserDetailsRowMapper;
import com.eightnote.nirvana.row_mappers.NirvanaUserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NirvanaUserDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public NirvanaUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(UserAccountDetails user) {
        String sql = "INSERT INTO NirvanaUsers VALUES ('%s', '%s', true)".formatted( user.getUsername(), user.getPassword());

        String sql_role = "INSERT INTO Authorities VALUES ('%s', '%s')".formatted(user.getUsername(), "user");
        jdbcTemplate.update(sql);
        jdbcTemplate.update(sql_role);

        String details_sql = "INSERT INTO UserDetails(username, first_name, last_name, date_of_birth, gender) VALUES ('%s', '%s', '%s', '%s', '%s');".formatted(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getGender()
        );

        jdbcTemplate.update(details_sql);

        for (int genre_id : user.getInterestIDs()) {
            String interests_sql = "INSERT INTO Interests(genre_id, username) VALUES ('%s', '%s')".formatted(genre_id, user.getUsername());
            jdbcTemplate.update(interests_sql);
        }
    }

    public NirvanaUser getUser(String username) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM (SELECT * FROM NirvanaUsers NATURAL JOIN Authorities) as user WHERE user.username = '%s'".formatted(username);
        return jdbcTemplate.queryForObject(sql, NirvanaUserRowMapper.nirvanaUserRowMapper);
    }

    public void removeUser(NirvanaUser user) {
        String sql = "DELETE FROM NirvanaUsers WHERE username = '%s'".formatted(user.getUsername());
        jdbcTemplate.update(sql);
    }

    public void getArtistDetail(NirvanaUser user) {
        String sql = "";
        jdbcTemplate.queryForObject(sql, NirvanaUserDetailsRowMapper.artistDetailsRowMapper);
    }

    public void createArtistAccount(ArtistAccountDetails ac) {
        String sql = "INSERT INTO NirvanaUsers VALUES ('%s', '%s', true)".formatted( ac.getUsername(), ac.getPassword());
        String sqlArtistDetails = "INSERT INTO Artist VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s')".formatted(
                ac.getUsername(),
                ac.getAbout(),
                ac.getTwitter(),
                ac.getFacebook(),
                ac.getInstagram(),
                ac.getNationality_id(),
                ac.getRecord_label_id()
                );

        String sql_role = "INSERT INTO Authorities VALUES ('%s', '%s')".formatted(ac.getUsername(), "artist");

        jdbcTemplate.update(sql);
        jdbcTemplate.update(sqlArtistDetails);
        jdbcTemplate.update(sql_role);
    }

    public List<ArtistDetails> getAllArtist(){
        String sql = "SELECT * FROM ArtistDetails";
        return jdbcTemplate.query(sql, NirvanaUserDetailsRowMapper.artistDetailsRowMapper);
    }
}
