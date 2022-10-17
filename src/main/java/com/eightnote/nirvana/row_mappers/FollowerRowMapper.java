package com.eightnote.nirvana.row_mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

public class FollowerRowMapper {
    @Autowired
    private static JdbcUserDetailsManager userDetailsManager;
    public static RowMapper<UserDetails>
        followerRowMapper =
            (rs, rowNum) -> userDetailsManager.loadUserByUsername(rs.getString("username"));
}
