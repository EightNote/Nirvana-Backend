package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.ArtistAccountDetails;
import com.eightnote.nirvana.models.ArtistDetails;
import com.eightnote.nirvana.models.NirvanaUser;
import com.eightnote.nirvana.row_mappers.ArtistDetailsRowMapper;
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

    public void getArtistDetail(NirvanaUser user) {
        String sql = "";
        jdbcTemplate.queryForObject(sql, ArtistDetailsRowMapper.artistDetailsRowMapper);
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
        return jdbcTemplate.query(sql,ArtistDetailsRowMapper.artistDetailsRowMapper);
    }

    public List<ArtistDetails> likedArtists(String username){
        String sql="SELECT * FROM Artist WHERE username IN (SELECT artist_id FROM ArtistLikes WHERE liked_by_id LIKE '%s');".formatted(username);
        return jdbcTemplate.query(sql,ArtistDetailsRowMapper.artistDetailsRowMapper);
    }
}
